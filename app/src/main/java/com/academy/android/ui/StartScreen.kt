package com.academy.android.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.academy.android.domain.models.AuthState
import com.academy.android.ui.auth.AuthOptionsScreen
import com.academy.android.ui.auth.AuthViewModel

@Composable
fun StartScreen(
    authVm: AuthViewModel = viewModel()
) {
    val authState by authVm.authState.collectAsState(initial = AuthState.LOADING)

    when (authState) {
        AuthState.AUTHORIZED, AuthState.GUEST -> HomeScreen()
        AuthState.UNAUTHORIZED -> AuthOptionsScreen(vm = authVm)
        AuthState.LOADING -> LoadingScreen()
    }
}