package com.trungcs.mini_app_handler.model

abstract class MiniApp(
    open val type: MiniAppType,
    open  val extraConfig: Map<String, String>,
)