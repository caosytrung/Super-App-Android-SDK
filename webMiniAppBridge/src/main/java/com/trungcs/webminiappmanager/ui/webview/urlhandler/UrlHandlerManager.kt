package com.trungcs.webminiappmanager.ui.webview.urlhandler

import android.content.Context
import android.webkit.WebView

class UrlHandlerManager(private val urlHandlers: List<UrlHandler>) {
    fun handleUrlOrNot(url: String?, webView: WebView?): Boolean {
        if (url == null || webView == null) return false

        return (handleUrlOrNot(url, webView.context))
    }

    private fun handleUrlOrNot(url: String, context: Context): Boolean {
        for (urlHandle in urlHandlers) {
            if (urlHandle.handleUrlOrNot(url, context)) return true
        }

        return false
    }

}