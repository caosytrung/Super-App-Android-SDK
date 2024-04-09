package com.trungcs.mini_app_bridge.model

abstract class MiniApp(
    open val type: MiniAppType,
    open  val extraConfig: Map<String, String>,
)