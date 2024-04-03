package com.trungcs.superapp.di

import com.trungcs.superapp.data.MiniAppListRepository
import com.trungcs.superapp.data.MiniAppListRepositoryImpl
import com.trungcs.superapp.data.datasource.DemoMiniAppDataSource
import com.trungcs.superapp.data.datasource.MiniAppDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    abstract fun bindMiniAppDataSource(
        miniAppRepo: DemoMiniAppDataSource,
    ): MiniAppDataSource

    @Binds
    abstract fun bindMiniAppListRepository(
        miniAppRepo: MiniAppListRepositoryImpl,
    ): MiniAppListRepository
}