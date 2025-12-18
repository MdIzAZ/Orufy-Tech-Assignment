package com.example.orufytechassignment.presentation.navigation


import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.orufytechassignment.presentation.ui.history.HistoryScreen
import com.example.orufytechassignment.presentation.ui.history.HistoryViewModel
import com.example.orufytechassignment.presentation.ui.home.HomeEvent
import com.example.orufytechassignment.presentation.ui.home.HomeScreen
import com.example.orufytechassignment.presentation.ui.home.HomeViewModel
import com.example.orufytechassignment.presentation.ui.webview.WebViewScreen
import com.example.orufytechassignment.presentation.ui.webview.WebViewViewModel

@Composable
fun AppNavGraph(
    navController: NavHostController
) {


    NavHost(
        navController = navController,
        startDestination = Route.Home
    ) {

        composable<Route.Home>() {
            val viewModel: HomeViewModel = hiltViewModel()
            val state = viewModel.state.collectAsStateWithLifecycle()

            HomeScreen(
                state = state.value,
                onEvent = viewModel::onEvent,
                navigateToWebView = {
                    navController.navigate(Route.WebView(it))
                },
                navigateToHistory = {
                    navController.navigate(Route.History)
                }
            )
        }

        composable<Route.WebView> {

            val webViewViewModel: WebViewViewModel = hiltViewModel()
            val webState = webViewViewModel.state.collectAsStateWithLifecycle()

            //get HomeViewModel from back stack
            val homeBackStackEntry = remember(it) {
                navController.getBackStackEntry(Route.Home)
            }
            val homeViewModel: HomeViewModel = hiltViewModel(homeBackStackEntry)

            WebViewScreen(
                state = webState.value,
                onEvent = webViewViewModel::onEvent,
                onBack = {
                    navController.popBackStack()
                },
                onClose = {
                    homeViewModel.onEvent(HomeEvent.OnUrlChanged(""))
                    navController.popBackStack(Route.Home, inclusive = false)
                }
            )
        }


        composable<Route.History>() {
            val viewModel: HistoryViewModel = hiltViewModel()
            val state = viewModel.state.collectAsStateWithLifecycle()

            HistoryScreen(
                state = state.value,
                onEvent = viewModel::onEvent,
                onBack = { navController.popBackStack() }
            )
        }
    }
}
