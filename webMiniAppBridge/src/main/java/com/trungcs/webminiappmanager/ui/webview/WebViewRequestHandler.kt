package com.trungcs.webminiappmanager.ui.webview

import com.trungcs.webminiappmanager.ui.webview.emiiter.EventEmitter
import com.trungcs.webminiappmanager.ui.webview.interceptor.CommandInterceptor


class WebViewRequestHandler(
    private val commandInterceptors: Map<String, CommandInterceptor> = mapOf(),
) {
    fun handleMiniAppRequest(
        eventEmitter: EventEmitter,
        commandName: String,
        subscriberId: String,
        request: String,
    ) {
        commandInterceptors[commandName]?.also {
            it.handleRequest(eventEmitter, subscriberId, request)
        }
    }
}
