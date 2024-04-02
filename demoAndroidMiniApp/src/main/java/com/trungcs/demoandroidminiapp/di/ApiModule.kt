package com.trungcs.demoandroidminiapp.di.data

import com.viettel.ekyc.data.DataManager
import com.viettel.ekyc.data.IDataManager
import com.viettel.ekyc.data.config.request.EkycConfig
import com.viettel.ekyc.data.network.api.KycApi
import com.viettel.ekyc.data.network.api.KycApi.Companion.DEFAULT_GSON
import com.viettel.ekyc.data.network.api.KycEndpoints.BASE_URL
import com.viettel.ekyc.data.network.interceptor.KycInterceptor
import com.viettel.ekyc.utils.apiBuilder
import dagger.Module
import dagger.Provides
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
internal object DataModule {

    @Provides
    @Singleton
    internal fun provideInterceptor(config: EkycConfig) =
        KycInterceptor(config.token, config.xRequestId, config.isDebug)

    @Provides
    @Singleton
    internal fun provideKycService(
        config: EkycConfig,
        interceptor: KycInterceptor
    ) = apiBuilder(KycApi::class.java) {
        baseUrl = if (config.proxyUrl.isNullOrBlank())  BASE_URL else config.proxyUrl
        client {

            logging {
                HttpLoggingInterceptor.Level.BODY
            }

            interceptors = listOf(interceptor)
        }
        converter {
            factory = GsonConverterFactory.create(DEFAULT_GSON)
        }
    }

    @Provides
    @Singleton
    internal fun provideDataManger(config: EkycConfig, kycApi: KycApi): IDataManager =
        DataManager(config, kycApi)
}