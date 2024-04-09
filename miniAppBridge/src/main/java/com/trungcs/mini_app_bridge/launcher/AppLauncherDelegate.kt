package com.trungcs.mini_app_bridge.launcher

import android.app.Application
import com.trungcs.mini_app_bridge.error.MiniAppError
import com.trungcs.mini_app_bridge.model.MiniApp
import com.trungcs.mini_app_bridge.model.MiniAppType

interface AppLauncherDelegate {
    val appType: MiniAppType

     fun startApp(
        application: Application,
        miniApp: MiniApp
    ): MiniAppError?
}