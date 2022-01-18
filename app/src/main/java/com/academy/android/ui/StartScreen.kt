package com.academy.android.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.academy.android.R
import com.academy.android.domain.OperationResult
import com.academy.android.domain.models.UserProfile
import com.academy.android.domain.models.UserTitle
import com.academy.android.ui.auth.AuthOptionsScreen
import com.academy.android.ui.auth.AuthViewModel

@Composable
fun StartScreen(vm: AuthViewModel = hiltViewModel(), navController: NavHostController) {
    val userProfileResult: OperationResult<UserProfile, Throwable?> by vm.userProfile.collectAsState(initial = OperationResult.Loading())

    when (userProfileResult) {
        is OperationResult.Loading -> LoadingScreen()
        is OperationResult.Success -> {
            when ((userProfileResult as OperationResult.Success).data.title) {
                UserTitle.MENTOR, UserTitle.STUDENT, UserTitle.GUEST -> HomeScreen(navController = navController)
                else -> AuthOptionsScreen(vm = vm)
            }
        }
        is OperationResult.Error -> ErrorScreen(reason = stringResource(id = R.string.profile_loading_error))
    }
}