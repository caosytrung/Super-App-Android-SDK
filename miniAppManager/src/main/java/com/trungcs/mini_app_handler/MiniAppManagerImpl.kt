package com.trungcs.mini_app_handler

import android.app.Application
import com.trungcs.base.utils.Result
import com.trungcs.mini_app_handler.error.MiniAppError
import com.trungcs.mini_app_handler.launcher.AppLauncherDelegate
import com.trungcs.mini_app_handler.model.MiniApp
import java.util.ServiceLoader

internal class MiniAppManagerImpl(val application: Application) : MiniAppManager {
    private val delegations by lazy {
        val delegateServiceLoaders = ServiceLoader.load(AppLauncherDelegate::class.java)
        delegateServiceLoaders.associateBy { it.appType }
    }

    override fun startApp(miniApp: MiniApp): Result<Unit, MiniAppError> {
        val delegation =
            delegations[miniApp.type] ?: return Result.failure(MiniAppError.CannotFindPackage)
        val error = delegation.startApp(application, miniApp)

        return if (error != null) Result.failure(error) else Result.success(Unit)
    }

}