package com.trungcs.webminiappmanager.ui.webview.interceptor

import com.trungcs.webminiappmanager.ui.webview.emiiter.EventEmitter

interface CommandInterceptor {
    fun getCommandName(): String
    fun handleRequest(
        eventEmitter: EventEmitter,
        subscriberId: String,
        requestString: String,
    )
}
