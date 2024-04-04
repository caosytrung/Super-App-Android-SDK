package com.trungcs.webminiappmanager.ui.webview.urlhandler

import android.content.Context
import android.content.Intent
import android.net.Uri

class CallActionUrlHandler : UrlHandler {
    override fun handleUrlOrNot(url: String, context: Context): Boolean {
        if (url.startsWith(CALL_ACTION_PREFIX)) {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse(url)
            context.startActivity(intent)

            return true
        }

        return false
    }

    companion object {
        private const val CALL_ACTION_PREFIX = "tel:"
    }
}