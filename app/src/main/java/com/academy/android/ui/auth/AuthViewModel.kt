package com.academy.android.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.academy.android.domain.models.AuthState
import com.academy.android.domain.use_cases.UserProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val userProfileUseCase: UserProfileUseCase
) : ViewModel() {
    val authState: Flow<AuthState>
        get() = userProfileUseCase.authState

    fun onGuestModeClick() {
        viewModelScope.launch {
            changeAuthState(AuthState.GUEST)
        }
    }

    private suspend fun changeAuthState(newAuthState: AuthState) {
        userProfileUseCase.changeAuthState(newAuthState)
    }
}