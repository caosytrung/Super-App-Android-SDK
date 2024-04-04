package com.trungcs.webminiappmanager.ui.webview.interceptor

import com.trungcs.webminiappmanager.ui.webview.emiiter.EventEmitter
import com.trungcs.webminiappmanager.ui.webview.utils.ResponseUtils

class GetExtraConfigCommandInterceptor(
    private val initParams: Map<String, String>,
) : CommandInterceptor {
    companion object {
        private const val COMMAND_NAME = "getExtraConfig"
    }

    override fun getCommandName() = COMMAND_NAME

    override fun handleRequest(
        eventEmitter: EventEmitter,
        subscriberId: String,
        requestString: String,
    ) {
        eventEmitter.sendResponseEvent(
            subscriberId, ResponseUtils.createSuccessResponse(initParams)
        )
    }
}