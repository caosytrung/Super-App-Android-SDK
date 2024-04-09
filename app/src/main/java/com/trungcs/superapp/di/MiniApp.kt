package com.trungcs.superapp.di

import android.app.Application
import android.content.Context
import com.trungcs.mini_app_bridge.MiniAppBridge
import com.trungcs.mini_app_bridge.MiniAppBridgeBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
class DispatchersModule {
    @Provides
    fun provideMiniAppManager(@ApplicationContext context: Context): MiniAppBridge =
        MiniAppBridgeBuilder().withApplication(context as Application).build()
}
