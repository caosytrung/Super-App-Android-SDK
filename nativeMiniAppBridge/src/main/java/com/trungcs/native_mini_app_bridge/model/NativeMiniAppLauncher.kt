package com.trungcs.native_mini_app_bridge.model

import android.app.Application
import android.content.Intent

interface NativeMiniAppLauncher {
    fun createIntent(application: Application, extraConfig: Map<String, String> = mapOf()): Intent
}