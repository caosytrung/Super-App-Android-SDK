package com.trungcs.webminiappmanager.ui.webview

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.webkit.JavascriptInterface
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import com.google.gson.GsonBuilder
import com.trungcs.webminiappmanager.ui.webview.emiiter.EventEmitter
import com.trungcs.webminiappmanager.webview.model.response.Response


@SuppressLint("SetJavaScriptEnabled")
class MiniAppWebView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null, defStyleAttr: Int = 0,
) : WebView(context, attrs, defStyleAttr) {

    companion object {
        const val MINI_APP_JS_INTERFACE = "miniAppJsInterface"
    }

    private val gson = GsonBuilder().create()
    private val eventEmitter: EventEmitter by lazy { createEventEmitter() }
    private var webViewCommandHandler: WebViewRequestHandler? = null

    fun init(
        customWebViewClient: WebViewClient? = null,
    ) {
        setWebContentsDebuggingEnabled(true)
        settings.apply {
            javaScriptEnabled = true
            domStorageEnabled = true
            useWideViewPort = true
            loadWithOverviewMode = true
        }

        webChromeClient = WebChromeClient()
        webViewClient = customWebViewClient ?: DefaultWebViewClient()
        isFocusable = true
        isFocusableInTouchMode = true

        initJavascriptInterface()
    }

    fun setWebViewCommandHandler(webViewRequestHandler: WebViewRequestHandler) {
        this.webViewCommandHandler = webViewRequestHandler
    }

    @SuppressLint("JavascriptInterface")
    private fun initJavascriptInterface() {
        addJavascriptInterface(this, MINI_APP_JS_INTERFACE)
    }

    /**
     * After invoke this function, js-mini-app will wait event-result by subscriberId
     *
     * @param commandName: type of command. For example: finish.
     * @param subscriberId: this id is to classify result from native side
     * @param request: extra data from mini-app
     */
    @JavascriptInterface
    fun invoke(
        commandName: String,
        subscriberId: String,
        request: String,
    ) {
        webViewCommandHandler?.handleMiniAppRequest(
            eventEmitter,
            commandName,
            subscriberId,
            request
        )
    }

    private fun createEventEmitter() = object : EventEmitter {
        override fun sendResponseEvent(receiverId: String, response: Response) {
            val str = gson.toJson(response)
            this@MiniAppWebView.post {
                evaluateJavascript("javascript:window.$receiverId(${str})") { _ -> }
            }
        }
    }

}