package com.trungcs.mini_app_bridge

import com.trungcs.base.utils.Result
import com.trungcs.mini_app_bridge.error.MiniAppError
import com.trungcs.mini_app_bridge.model.MiniApp

interface MiniAppBridge {
    fun startApp(miniApp: MiniApp): Result<Unit, MiniAppError>
}