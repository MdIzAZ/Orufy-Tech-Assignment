package com.example.orufytechassignment.presentation.navigation


import kotlinx.serialization.Serializable

@Serializable
sealed class Route {
    @Serializable
    data object Home : Route()

    @Serializable
    data class WebView(val url: String) : Route()

    @Serializable
    data object History : Route()
}

