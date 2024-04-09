package com.trungcs.webminiappmanager

import com.trungcs.mini_app_bridge.launcher.AppLauncherDelegate
import com.trungcs.mini_app_bridge.launcher.AppLauncherDelegateFactory

class WebAppLauncherDelegateFactory : AppLauncherDelegateFactory {
    override fun create(): AppLauncherDelegate {
        return WebAppLauncherDelegate()
    }
}