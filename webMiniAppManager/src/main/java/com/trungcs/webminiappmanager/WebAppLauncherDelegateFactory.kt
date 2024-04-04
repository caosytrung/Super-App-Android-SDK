package com.trungcs.webminiappmanager

import com.trungcs.mini_app_handler.launcher.AppLauncherDelegate
import com.trungcs.mini_app_handler.launcher.AppLauncherDelegateFactory

class WebAppLauncherDelegateFactory : AppLauncherDelegateFactory {
    override fun create(): AppLauncherDelegate {
        return WebAppLauncherDelegate()
    }
}