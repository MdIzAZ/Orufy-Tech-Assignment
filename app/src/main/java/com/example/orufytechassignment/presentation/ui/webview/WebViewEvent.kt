package com.example.orufytechassignment.presentation.ui.webview

sealed interface WebViewEvent {

    data class OnPageStarted(val url: String) : WebViewEvent

    data class OnPageFinished(val url: String) : WebViewEvent

    object OnBackClicked : WebViewEvent

    object OnCloseClicked : WebViewEvent
}
