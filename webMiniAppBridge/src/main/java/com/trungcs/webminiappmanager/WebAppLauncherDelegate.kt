package com.trungcs.webminiappmanager

import android.app.Application
import com.trungcs.mini_app_bridge.error.MiniAppError
import com.trungcs.mini_app_bridge.launcher.AppLauncherDelegate
import com.trungcs.mini_app_bridge.model.MiniApp
import com.trungcs.mini_app_bridge.model.MiniAppType
import com.trungcs.webminiappmanager.model.WebMiniApp
import com.trungcs.webminiappmanager.ui.miniAppActivity.WebMiniAppActivity

class WebAppLauncherDelegate : AppLauncherDelegate {
    override val appType: MiniAppType
        get() = MiniAppType.WEB

    override fun startApp(application: Application, miniApp: MiniApp): MiniAppError? {
        val webMiniApp = miniApp as? WebMiniApp ?: return MiniAppError.WrongAppType
        WebMiniAppActivity.start(application, webMiniApp)
        return null
    }
}