package com.trungcs.mini_app_bridge.error


sealed class MiniAppError : Throwable() {
    object CannotFindPackage : MiniAppError()
    object WrongAppType : MiniAppError()
    object FailedToLoadMiniAppLauncher : MiniAppError()
}