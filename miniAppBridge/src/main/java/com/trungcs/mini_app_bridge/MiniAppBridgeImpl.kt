package com.trungcs.mini_app_bridge

import android.app.Application
import com.trungcs.base.utils.Result
import com.trungcs.mini_app_bridge.error.MiniAppError
import com.trungcs.mini_app_bridge.launcher.AppLauncherDelegateFactory
import com.trungcs.mini_app_bridge.model.MiniApp
import java.util.ServiceLoader

internal class MiniAppBridgeImpl(private val application: Application) : MiniAppBridge {
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