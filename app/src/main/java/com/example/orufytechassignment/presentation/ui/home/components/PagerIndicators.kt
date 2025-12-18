package com.example.orufytechassignment.presentation.ui.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PagerIndicators(
    total: Int,
    current: Int
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        repeat(total) { index ->
            Box(
                modifier = Modifier
                    .padding(4.dp)
                    .size(
                        if (index == current) 10.dp else 8.dp
                    )
                    .background(
                        color = if (index == current)
                            MaterialTheme.colorScheme.primary
                        else
                            MaterialTheme.colorScheme.onSurfaceVariant,
                        shape = MaterialTheme.shapes.small
                    )
            )
        }
    }
}
