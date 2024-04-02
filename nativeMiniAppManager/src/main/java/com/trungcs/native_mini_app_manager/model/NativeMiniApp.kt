package com.trungcs.native_mini_app_manager.model

import com.trungcs.mini_app_handler.model.MiniApp
import com.trungcs.mini_app_handler.model.MiniAppType

data class NativeMiniApp(
    // className of miniApp to load
    val miniAppClassName: String,
    override val extraConfig: Map<String, String> = mutableMapOf(),
) : MiniApp(MiniAppType.NATIVE, extraConfig)