package com.trungcs.native_mini_app_bridge.model

import com.trungcs.mini_app_bridge.model.MiniApp
import com.trungcs.mini_app_bridge.model.MiniAppType

data class NativeMiniApp(
    // className of miniApp to load
    val miniAppClassName: String,
    override val extraConfig: Map<String, String> = mutableMapOf(),
) : MiniApp(MiniAppType.NATIVE, extraConfig)