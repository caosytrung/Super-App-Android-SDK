package com.trungcs.webminiappmanager

import android.app.Application
import com.trungcs.mini_app_handler.error.MiniAppError
import com.trungcs.mini_app_handler.launcher.AppLauncherDelegate
import com.trungcs.mini_app_handler.model.MiniApp
import com.trungcs.mini_app_handler.model.MiniAppType
import com.trungcs.webminiappmanager.model.WebMiniApp
import com.trungcs.webminiappmanager.ui.miniAppActivity.MiniAppActivity

class WebAppLauncherDelegate : AppLauncherDelegate {
    override val appType: MiniAppType
        get() = MiniAppType.WEB

    override fun startApp(application: Application, miniApp: MiniApp): MiniAppError? {
        val webMiniApp = miniApp as? WebMiniApp ?: return MiniAppError.WrongAppType

        MiniAppActivity.start(application, webMiniApp)
        return null
    }
}