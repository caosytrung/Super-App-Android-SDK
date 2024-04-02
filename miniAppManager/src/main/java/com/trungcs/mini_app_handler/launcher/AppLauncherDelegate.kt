package com.trungcs.mini_app_handler.launcher

import android.app.Application
import com.trungcs.mini_app_handler.error.MiniAppError
import com.trungcs.mini_app_handler.model.MiniApp
import com.trungcs.mini_app_handler.model.MiniAppType

interface AppLauncherDelegate {
    val appType: MiniAppType

     fun startApp(
        application: Application,
        miniApp: MiniApp
    ): MiniAppError?
}