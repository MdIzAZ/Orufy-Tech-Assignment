package com.example.orufytechassignment.presentation.ui.webview

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class WebViewViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val initialUrl: String = savedStateHandle["url"] ?: ""

    private val _state = MutableStateFlow(WebViewState(currentUrl = initialUrl))
    val state: StateFlow<WebViewState> = _state.asStateFlow()

    fun setInitialUrl(url: String) {
        if (_state.value.currentUrl.isEmpty()) {
            _state.value = _state.value.copy(currentUrl = url)
        }
    }

    fun onEvent(event: WebViewEvent) {
        when (event) {
            is WebViewEvent.OnPageStarted -> {
                _state.value = _state.value.copy(
                    currentUrl = event.url,
                    isLoading = true
                )
            }
            is WebViewEvent.OnPageFinished -> {
                _state.value = _state.value.copy(
                    currentUrl = event.url,
                    isLoading = false
                )
            }
            WebViewEvent.OnBackClicked,
            WebViewEvent.OnCloseClicked -> Unit
        }
    }
}
