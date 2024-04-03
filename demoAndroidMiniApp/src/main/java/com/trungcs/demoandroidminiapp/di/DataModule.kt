package com.trungcs.demoandroidminiapp.di

import com.trungcs.demoandroidminiapp.data.product.ProductRepository
import com.trungcs.demoandroidminiapp.data.product.ProductRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    abstract fun bindProductRepository(
        productServiceImpl: ProductRepositoryImpl,
    ): ProductRepository
}