package com.academy.android.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.academy.android.domain.OperationResult
import com.academy.android.domain.models.UserProfile
import com.academy.android.domain.use_cases.UserProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val userProfileUseCase: UserProfileUseCase
) : ViewModel() {
    val userProfile: Flow<OperationResult<UserProfile, Throwable?>> =
        userProfileUseCase.userProfile

    fun onLogInClick(username: String, password: String) {
        viewModelScope.launch {
            userProfileUseCase.login(username = username, password = password)
        }
    }

    fun onGuestModeClick() {
        viewModelScope.launch {
            userProfileUseCase.enterGuestMode()
        }
    }
}