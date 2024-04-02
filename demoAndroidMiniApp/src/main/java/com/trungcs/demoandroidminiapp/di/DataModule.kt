package com.trungcs.demoandroidminiapp.di

import com.trungcs.demoandroidminiapp.data.product.ProductRepository
import com.trungcs.demoandroidminiapp.data.product.ProductRepositoryImpl
import com.trungcs.demoandroidminiapp.remote.Endpoint.BASE_URL
import com.trungcs.demoandroidminiapp.remote.ProductApi
import com.trungcs.demoandroidminiapp.remote.ProductApi.Companion.DEFAULT_GSON
import com.trungcs.demoandroidminiapp.utils.apiBuilder
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    abstract fun bindAnalyticsService(
        analyticsServiceImpl: ProductRepositoryImpl,
    ): ProductRepository
}