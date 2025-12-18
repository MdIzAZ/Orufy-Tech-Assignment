package com.example.orufytechassignment.presentation.ui.webview

import android.annotation.SuppressLint
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("SetJavaScriptEnabled")
@Composable
fun WebViewScreen(
    state: WebViewState,
    onEvent: (WebViewEvent) -> Unit,
    onBack: () -> Unit,
    onClose: () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {

        TopAppBar(
            title = {
                Text(
                    text = state.currentUrl.ifEmpty { "Loading..." },
                    maxLines = 1
                )
            },
            navigationIcon = {
                IconButton(onClick = {
                    onEvent(WebViewEvent.OnBackClicked)
                    onBack()
                }) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                }
            },
            actions = {
                IconButton(onClick = {
                    onEvent(WebViewEvent.OnCloseClicked)
                    onClose()
                }) {
                    Icon(Icons.Default.Close, contentDescription = "Close")
                }
            }
        )

        AndroidView(
            modifier = Modifier.fillMaxSize(),
            factory = { context ->
                WebView(context).apply {

                    settings.javaScriptEnabled = true
                    settings.domStorageEnabled = true

                    webViewClient = object : WebViewClient() {
                        override fun onPageStarted(
                            view: WebView?,
                            url: String?,
                            favicon: android.graphics.Bitmap?
                        ) {
                            url?.let { onEvent(WebViewEvent.OnPageStarted(it)) }
                        }

                        override fun onPageFinished(
                            view: WebView?,
                            url: String?
                        ) {
                            url?.let { onEvent(WebViewEvent.OnPageFinished(it)) }
                        }
                    }

                    webChromeClient = WebChromeClient()
                    loadUrl(state.currentUrl)
                }
            }
        )



        if (state.isLoading) {
            LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
        }
    }
}
