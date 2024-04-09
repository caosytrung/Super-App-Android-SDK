package com.trungcs.webminiappmanager.ui.miniAppActivity

import android.graphics.Bitmap
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.trungcs.webminiappmanager.ui.webview.MiniAppWebView
import com.trungcs.webminiappmanager.ui.webview.WebViewRequestHandler
import com.trungcs.webminiappmanager.ui.webview.WebViewRequestHandlerBuilder
import com.trungcs.webminiappmanager.ui.webview.interceptor.GetExtraConfigCommandInterceptor
import com.trungcs.webminiappmanager.ui.webview.interceptor.SendFinishResultCommandInterceptor
import com.trungcs.webminiappmanager.ui.webview.urlhandler.CallActionUrlHandler
import com.trungcs.webminiappmanager.ui.webview.urlhandler.UrlHandlerManager

@Composable
fun MiniAppScreen(
    url: String,
    extraConfig: Map<String, String>,
    viewModel: MiniAppViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    onFinishActivity: () -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        AndroidView(
            factory = { context ->
                MiniAppWebView(context)
            },
            modifier = Modifier.fillMaxSize(),
            update = { webView ->
                webView.apply {
                    init(
                        customWebViewClient = buildWebViewClient(
                            { viewModel.setLoading(true) },
                            { viewModel.setLoading(false) }),
                    )

                    setWebViewCommandHandler(
                        buildWebViewCommandHandler(
                            onFinishActivity,
                            extraConfig
                        )
                    )

                    loadUrl(url)
                }

            }
        )

        LoadingView(viewModel = viewModel)
    }
}

@Composable
fun LoadingView(viewModel: MiniAppViewModel) {
    val isLoading by viewModel.isLoading.collectAsState()
    if (isLoading) CircularProgressIndicator()

}

fun buildWebViewCommandHandler(
    onFinishActivity: () -> Unit,
    extraConfig: Map<String, String>,
): WebViewRequestHandler {
    return WebViewRequestHandlerBuilder().withCommandInterceptors(
        listOf(
            SendFinishResultCommandInterceptor(onFinishActivity),
            GetExtraConfigCommandInterceptor(extraConfig)
        )
    ).build()
}

private fun buildWebViewClient(onStart: () -> Unit, onFinished: () -> Unit) =
    object : WebViewClient() {
        private val urlHandlerManager by lazy {
            UrlHandlerManager(listOf(CallActionUrlHandler()))
        }

        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)
            onStart()
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            onFinished()
        }

        @Deprecated("Deprecated in Java")
        override fun shouldOverrideUrlLoading(
            view: WebView?,
            url: String?,
        ): Boolean {
            if (urlHandlerManager.handleUrlOrNot(url, view)) return true

            return super.shouldOverrideUrlLoading(view, url)
        }
    }