package com.trungcs.native_mini_app_manager

import android.app.Application
import android.content.Intent
import com.trungcs.base.utils.Result
import com.trungcs.mini_app_handler.error.MiniAppError
import com.trungcs.mini_app_handler.launcher.AppLauncherDelegate
import com.trungcs.mini_app_handler.model.MiniApp
import com.trungcs.mini_app_handler.model.MiniAppType
import com.trungcs.native_mini_app_manager.model.NativeMiniApp
import com.trungcs.native_mini_app_manager.model.NativeMiniAppLauncher

class NativeAppLauncherDelegate : AppLauncherDelegate {
    override val appType: MiniAppType
        get() = MiniAppType.NATIVE

    override fun startApp(application: Application, miniApp: MiniApp): MiniAppError? {
        val nativeMiniApp = miniApp as? NativeMiniApp ?: return MiniAppError.WrongAppType
        val launcherResult = loadAppLauncher(nativeMiniApp.miniAppClassName)
        if (launcherResult.isFailure()) return launcherResult.exception()

        launcherResult.get().create(application, miniApp.extraConfig).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
            application.startActivity(this)
        }

        return null
    }


    private fun loadAppLauncher(className: String): Result<NativeMiniAppLauncher, MiniAppError> {
        return try {
            val launcherClass = Class.forName(className)
            val launcher =
                launcherClass.getDeclaredConstructor().newInstance() as NativeMiniAppLauncher
            Result.success(launcher)
        } catch (e: Exception) {
            Result.failure(MiniAppError.FailedToLoadMiniAppLauncher)
        }
    }
}