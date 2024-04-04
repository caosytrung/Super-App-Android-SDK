package com.trungcs.webminiappmanager.ui.webview

import com.trungcs.webminiappmanager.ui.webview.interceptor.CommandInterceptor

class WebViewRequestHandlerBuilder {
    private var commandInterceptors: Map<String, CommandInterceptor> = mapOf()

    fun withCommandInterceptors(commandInterceptors: List<CommandInterceptor>) = apply {
        this.commandInterceptors = commandInterceptors.toMap()
    }


    fun build() = WebViewRequestHandler(
        commandInterceptors = commandInterceptors
    )

    private fun List<CommandInterceptor>.toMap(): Map<String, CommandInterceptor> {
        return this.associateBy { it.getCommandName() }
    }
}