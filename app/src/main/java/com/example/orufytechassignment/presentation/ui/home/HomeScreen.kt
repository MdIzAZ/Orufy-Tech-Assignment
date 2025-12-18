package com.example.orufytechassignment.presentation.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.orufytechassignment.R
import com.example.orufytechassignment.presentation.ui.home.components.HomeCarousel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    state: HomeState,
    onEvent: (HomeEvent) -> Unit,
    navigateToWebView: (String) -> Unit,
    navigateToHistory: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Home") },
                actions = {
                    IconButton(
                        onClick = navigateToHistory,
                        content = {
                            Icon(
                                modifier = Modifier.size(28.dp),
                                painter = painterResource(R.drawable.history),
                                contentDescription = null,
                                tint = Color.Unspecified
                            )
                        }
                    )

                }
            )
        }
    ) { padding ->



        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxWidth()
        ) {

            HomeCarousel()

            Spacer(modifier = Modifier.height(24.dp))


            OutlinedTextField(
                value = state.urlInput,
                onValueChange = {
                    onEvent(HomeEvent.OnUrlChanged(it))
                },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Enter URL") },
                isError = state.error != null,
            )

            state.error?.let {
                Text(
                    text = it,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    onEvent(HomeEvent.OnOpenClicked)


                    if (state.error == null && state.urlInput.isNotBlank()) {
                        val url = state.urlInput.trim().let {
                            if (it.startsWith("http")) it else "https://$it"
                        }

                        navigateToWebView(url)
                    }
                }
            ) {
                Text("Open")
            }
        }
    }
}
