package com.example.orufytechassignment.presentation.ui.history

import com.example.orufytechassignment.domain.models.VisitedUrl

data class HistoryState(
    val items: List<VisitedUrl> = emptyList(),
    val isUploading: Boolean = false,
    val uploadSuccess: Boolean = false,
    val showDeleteDialog: Boolean = false,
    val error: String? = null
)

