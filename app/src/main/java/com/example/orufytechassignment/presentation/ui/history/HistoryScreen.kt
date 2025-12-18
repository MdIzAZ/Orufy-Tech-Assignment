package com.example.orufytechassignment.presentation.ui.history

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.orufytechassignment.R
import com.example.orufytechassignment.presentation.ui.history.components.DeleteConfirmationDialog
import com.example.orufytechassignment.presentation.ui.history.components.HistoryItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryScreen(
    state: HistoryState,
    onEvent: (HistoryEvent) -> Unit,
    onBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("History") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    Row() {
                        IconButton(
                            onClick = { onEvent(HistoryEvent.OnDeleteClicked) },
                            content = {
                                Icon(
                                    modifier = Modifier.size(28.dp),
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = null,
                                    tint = Color.Unspecified
                                )
                            }
                        )

                        IconButton(
                            onClick = { onEvent(HistoryEvent.OnUploadClicked) },
                            content = {
                                Icon(
                                    modifier = Modifier.size(26.dp),
                                    painter = painterResource(R.drawable.upload),
                                    contentDescription = null,
                                    tint = Color.Unspecified
                                )
                            }
                        )

                    }
                }
            )
        }
    ) { padding ->


        if (state.showDeleteDialog) {
            DeleteConfirmationDialog(
                onConfirm = {
                    onEvent(HistoryEvent.OnDeleteConfirm)
                },
                onDismiss = {
                    onEvent(HistoryEvent.OnDeleteDismiss)
                }
            )
        }




        if (state.items.isEmpty()) {
            Box(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize(),
                contentAlignment = androidx.compose.ui.Alignment.Center
            ) {
                Text("No history available")
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
            ) {
                items(state.items) { item ->
                    HistoryItem(item)
                }
            }
        }
    }
}
