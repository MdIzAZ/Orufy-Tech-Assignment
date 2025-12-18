package com.example.orufytechassignment.presentation.ui.home


sealed interface HomeEvent {
    data class OnUrlChanged(val value: String) : HomeEvent
    object OnOpenClicked : HomeEvent
    object OnHistoryClicked : HomeEvent
    data class OnValidationSuccess(val validUrl: String) : HomeEvent
}

