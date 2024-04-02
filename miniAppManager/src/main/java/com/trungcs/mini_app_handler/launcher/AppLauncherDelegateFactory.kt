package com.trungcs.mini_app_handler.launcher


// Using ServiceLoader to load implemented mini-app delegated factories.
interface AppLauncherDelegateFactory {
    fun create(): AppLauncherDelegate
}