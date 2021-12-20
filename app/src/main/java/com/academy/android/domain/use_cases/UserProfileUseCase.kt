package com.academy.android.domain.use_cases

import com.academy.android.domain.models.AuthState
import com.academy.android.domain.repositories.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserProfileUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    val authState: Flow<AuthState> =
        authRepository.authState

    suspend fun changeAuthState(newAuthState: AuthState) {
        authRepository.changeAuthState(newAuthState)
    }
}