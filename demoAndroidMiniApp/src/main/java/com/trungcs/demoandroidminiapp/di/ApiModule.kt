package com.trungcs.demoandroidminiapp.di

import com.trungcs.demoandroidminiapp.remote.Endpoint.BASE_URL
import com.trungcs.demoandroidminiapp.remote.ProductApi
import com.trungcs.demoandroidminiapp.remote.ProductApi.Companion.DEFAULT_GSON
import com.trungcs.demoandroidminiapp.utils.apiBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    @Provides
    @ProductApiAnnotation
    fun provideProductAPI(): ProductApi = apiBuilder(ProductApi::class.java) {
        baseUrl = BASE_URL

        converter {
            factory = GsonConverterFactory.create(DEFAULT_GSON)
        }
    }
}

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class ProductApiAnnotation