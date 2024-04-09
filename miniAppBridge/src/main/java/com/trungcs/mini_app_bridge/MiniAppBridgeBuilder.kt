package com.trungcs.mini_app_bridge

import android.app.Application

class MiniAppBridgeBuilder {
    private lateinit var application: Application

    fun withApplication(application: Application): MiniAppBridgeBuilder = apply {
        this.application = application
    }

    fun build(): MiniAppBridge {
        return MiniAppBridgeImpl(application)
    }
}