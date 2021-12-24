package com.academy.android.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.academy.android.domain.models.AuthState
import com.academy.android.ui.auth.AuthOptionsScreen
import com.academy.android.ui.auth.AuthViewModel

@Composable
fun StartScreen(vm: AuthViewModel = hiltViewModel(), navController: NavHostController) {
    val authState by vm.authState.collectAsState(initial = AuthState.LOADING)

    when (authState) {
        AuthState.AUTHORIZED, AuthState.GUEST -> HomeScreen(navController = navController)
        AuthState.UNAUTHORIZED -> AuthOptionsScreen(vm = vm)
        AuthState.LOADING -> LoadingScreen()
    }
}