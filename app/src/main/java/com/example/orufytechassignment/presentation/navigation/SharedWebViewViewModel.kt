package com.example.orufytechassignment.presentation.navigation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class SharedWebViewViewModel : ViewModel() {

    private val _url = MutableStateFlow<String?>(null)
    val url: StateFlow<String?> = _url.asStateFlow()

    fun setUrl(url: String) {
        _url.value = url
    }

    fun clear() {
        _url.value = null
    }
}
