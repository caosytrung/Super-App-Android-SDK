package com.trungcs.native_mini_app_manager.model

import android.app.Application
import android.content.Intent

interface NativeMiniAppLauncher {
    fun create(application: Application, extraConfig: Map<String, String> = mapOf()): Intent
}