package com.trungcs.webminiappmanager.ui.webview.emiiter

import com.trungcs.webminiappmanager.webview.model.response.Response

interface EventEmitter {
    fun sendResponseEvent(receiverId: String, response: Response)
}
