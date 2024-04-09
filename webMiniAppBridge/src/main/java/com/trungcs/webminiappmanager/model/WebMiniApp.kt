package com.trungcs.webminiappmanager.model

import com.trungcs.mini_app_bridge.model.MiniApp
import com.trungcs.mini_app_bridge.model.MiniAppType

data class WebMiniApp(
    val url: String,
    override val extraConfig: Map<String, String> = mutableMapOf(),
) : MiniApp(MiniAppType.WEB, extraConfig)