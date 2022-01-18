package com.academy.android.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.academy.android.domain.OperationResult
import com.academy.android.domain.models.AuthState
import com.academy.android.domain.models.UserProfile
import com.academy.android.domain.use_cases.UserProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.net.HttpURLConnection
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val userProfileUseCase: UserProfileUseCase
) : ViewModel() {
    val userProfile: Flow<OperationResult<UserProfile, Throwable?>> =
        userProfileUseCase.userProfile

    private val _authState = MutableStateFlow(AuthState.INITIAL)
    val authState: StateFlow<AuthState> = _authState

    fun onLogInClick(username: String, password: String) {
        viewModelScope.launch {
            _authState.value = AuthState.LOADING
            when (val loginResponse = userProfileUseCase.login(username = username, password = password)) {
                is OperationResult.Success -> _authState.value = AuthState.AUTHORIZED
                is OperationResult.Error -> {
                    when (loginResponse.data) {
                        is HttpException -> if (loginResponse.data.code() == HttpURLConnection.HTTP_UNAUTHORIZED) {
                            _authState.value = AuthState.UNAUTHORIZED
                        }
                        else -> _authState.value = AuthState.EXCEPTION
                    }
                }
            }
        }
    }

    fun onGuestModeClick() {
        viewModelScope.launch {
            userProfileUseCase.enterGuestMode()
        }
    }
}