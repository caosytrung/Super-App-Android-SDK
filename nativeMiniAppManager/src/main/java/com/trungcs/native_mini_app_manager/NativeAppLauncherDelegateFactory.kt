package com.trungcs.native_mini_app_manager

import com.trungcs.mini_app_handler.launcher.AppLauncherDelegate
import com.trungcs.mini_app_handler.launcher.AppLauncherDelegateFactory

class NativeAppLauncherDelegateFactory: AppLauncherDelegateFactory {
    override fun create(): AppLauncherDelegate {
        return NativeAppLauncherDelegate()
    }
}