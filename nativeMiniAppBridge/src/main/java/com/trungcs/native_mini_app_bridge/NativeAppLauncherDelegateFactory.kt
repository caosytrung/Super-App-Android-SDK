package com.trungcs.native_mini_app_bridge

import com.trungcs.mini_app_bridge.launcher.AppLauncherDelegate
import com.trungcs.mini_app_bridge.launcher.AppLauncherDelegateFactory

class NativeAppLauncherDelegateFactory: AppLauncherDelegateFactory {
    override fun create(): AppLauncherDelegate {
        return NativeAppLauncherDelegate()
    }
}