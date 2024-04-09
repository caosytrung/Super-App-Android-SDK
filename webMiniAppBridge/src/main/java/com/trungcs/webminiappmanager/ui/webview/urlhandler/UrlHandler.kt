package com.trungcs.webminiappmanager.ui.webview.urlhandler

import android.content.Context

interface UrlHandler {
    fun handleUrlOrNot(url: String, context: Context): Boolean
}