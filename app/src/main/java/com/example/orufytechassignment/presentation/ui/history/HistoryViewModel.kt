package com.example.orufytechassignment.presentation.ui.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.orufytechassignment.domain.repository.HistoryRepository
import com.example.orufytechassignment.domain.repository.VisitedUrlRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val visitedUrlRepository: VisitedUrlRepository,
    private val historyRepository: HistoryRepository
) : ViewModel() {

    private val _state = MutableStateFlow(HistoryState())
    val state: StateFlow<HistoryState> = _state.asStateFlow()

    init {
        observeHistory()
    }

    private fun observeHistory() {
        viewModelScope.launch {
            visitedUrlRepository.getHistory().collect { list ->
                _state.value = _state.value.copy(items = list)
            }
        }
    }

    fun onEvent(event: HistoryEvent) {
        when (event) {
            HistoryEvent.OnDeleteClicked -> {
                _state.value = _state.value.copy(
                    showDeleteDialog = true
                )
            }

            HistoryEvent.OnDeleteConfirm -> {
                viewModelScope.launch {
                    visitedUrlRepository.clearHistory()
                }
                _state.value = _state.value.copy(
                    showDeleteDialog = false
                )
            }

            HistoryEvent.OnDeleteDismiss -> {
                _state.value = _state.value.copy(
                    showDeleteDialog = false
                )
            }

            HistoryEvent.OnUploadClicked -> {
                viewModelScope.launch {
                    _state.value = _state.value.copy(
                        isUploading = true,
                        error = null
                    )

                    try {
                        val urlStrings = _state.value.items.map { it.url }
                        historyRepository.uploadHistory(urlStrings)

                        _state.value = _state.value.copy(
                            isUploading = false,
                            uploadSuccess = true
                        )
                    } catch (e: Exception) {
                        _state.value = _state.value.copy(
                            isUploading = false,
                            error = e.message
                        )
                    }
                }
            }
        }

    }
}
