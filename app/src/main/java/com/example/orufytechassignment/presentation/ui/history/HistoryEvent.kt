
package com.example.orufytechassignment.presentation.ui.history

sealed interface HistoryEvent {
    data object OnDeleteClicked : HistoryEvent
    data object OnDeleteConfirm : HistoryEvent
    data object OnDeleteDismiss : HistoryEvent
    object OnUploadClicked : HistoryEvent
}
