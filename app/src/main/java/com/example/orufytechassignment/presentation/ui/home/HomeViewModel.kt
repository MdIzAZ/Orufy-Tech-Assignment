package com.example.orufytechassignment.presentation.ui.home

import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.orufytechassignment.domain.repository.VisitedUrlRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: VisitedUrlRepository
) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state.asStateFlow()

    fun onEvent(event: HomeEvent) {
        when (event) {

            is HomeEvent.OnUrlChanged -> {
                _state.value = _state.value.copy(
                    urlInput = event.value,
                    error = null
                )
            }

            HomeEvent.OnOpenClicked -> {
                validateAndProceed()
            }

            is HomeEvent.OnValidationSuccess -> {
                viewModelScope.launch {
                    repository.saveUrl(event.validUrl)
                }
            }

            HomeEvent.OnHistoryClicked -> Unit
        }
    }

    private fun validateAndProceed() {
        val raw = _state.value.urlInput.trim()

        if (raw.isEmpty()) {
            _state.value = _state.value.copy(
                error = "URL cannot be empty"
            )
            return
        }

        val finalUrl =
            if (raw.startsWith("http://") || raw.startsWith("https://"))
                raw
            else
                "https://$raw"

        if (!Patterns.WEB_URL.matcher(finalUrl).matches()) {
            _state.value = _state.value.copy(
                error = "Please enter a valid URL"
            )
            return
        }

        _state.value = _state.value.copy(error = null)
        onEvent(HomeEvent.OnValidationSuccess(finalUrl))
    }
}
