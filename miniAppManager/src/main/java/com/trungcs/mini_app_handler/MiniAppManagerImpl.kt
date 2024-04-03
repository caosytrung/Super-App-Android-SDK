package com.trungcs.mini_app_handler

import android.app.Application
import com.trungcs.base.utils.Result
import com.trungcs.mini_app_handler.error.MiniAppError
import com.trungcs.mini_app_handler.launcher.AppLauncherDelegateFactory
import com.trungcs.mini_app_handler.model.MiniApp
import java.util.ServiceLoader

internal class MiniAppManagerImpl(private val application: Application) : MiniAppManager {
    private val delegations by lazy {
        val delegateServiceLoaders = ServiceLoader.load(AppLauncherDelegateFactory::class.java)
        delegateServiceLoaders.map { it.create() }.associateBy { it.appType }
    }

    override fun startApp(miniApp: MiniApp): Result<Unit, MiniAppError> {
        val delegation =
            delegations[miniApp.type] ?: return Result.failure(MiniAppError.CannotFindPackage)
        val error = delegation.startApp(application, miniApp)

        return if (error != null) Result.failure(error) else Result.success(Unit)
    }

}