package com.trungcs.mini_app_handler

import com.trungcs.base.utils.Result
import com.trungcs.mini_app_handler.error.MiniAppError
import com.trungcs.mini_app_handler.model.MiniApp

interface MiniAppManager {
    fun startApp(miniApp: MiniApp): Result<Unit, MiniAppError>
}