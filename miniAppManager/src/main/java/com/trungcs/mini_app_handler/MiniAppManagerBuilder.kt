package com.trungcs.mini_app_handler

import android.app.Application

class MiniAppManagerBuilder {
    private lateinit var application: Application

    fun withApplication(application: Application): MiniAppManagerBuilder = apply {
        this.application = application
    }

    fun build(): MiniAppManager {
        return MiniAppManagerImpl(application)
    }
}