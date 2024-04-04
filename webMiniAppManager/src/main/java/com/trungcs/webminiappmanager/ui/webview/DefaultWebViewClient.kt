package com.trungcs.webminiappmanager.ui.webview

import android.webkit.WebView
import android.webkit.WebViewClient
import com.trungcs.webminiappmanager.ui.webview.urlhandler.CallActionUrlHandler
import com.trungcs.webminiappmanager.ui.webview.urlhandler.UrlHandlerManager

internal class DefaultWebViewClient : WebViewClient() {
    private val urlHandlerManager by lazy {
        UrlHandlerManager(listOf(CallActionUrlHandler()))
    }

    override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
        if (urlHandlerManager.handleUrlOrNot(url, view)) return true

        return super.shouldOverrideUrlLoading(view, url)
    }
}