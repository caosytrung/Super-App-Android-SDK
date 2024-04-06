package com.trungcs.webminiappmanager.ui.webview.interceptor

import com.trungcs.webminiappmanager.ui.webview.emiiter.EventEmitter

class SendFinishResultCommandInterceptor(
    private val onFinishMiniApp: (() -> Unit)?,
) : CommandInterceptor {

    companion object {
        private const val COMMAND_NAME = "finishMiniApp"
    }

    override fun getCommandName() = COMMAND_NAME

    override fun handleRequest(
        eventEmitter: EventEmitter,
        subscriberId: String,
        requestString: String,
    ) {
        onFinishMiniApp?.invoke()
    }
}