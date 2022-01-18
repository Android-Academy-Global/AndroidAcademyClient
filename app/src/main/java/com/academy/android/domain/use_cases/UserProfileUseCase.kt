package com.academy.android.domain.use_cases

import com.academy.android.domain.OperationResult
import com.academy.android.domain.models.UserProfile
import com.academy.android.domain.repositories.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserProfileUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    val userProfile: Flow<OperationResult<UserProfile, Throwable?>> =
        authRepository.userProfile

    suspend fun login(username: String, password: String): OperationResult<Unit, Throwable?> =
        authRepository.login(username = username, password = password)

    suspend fun enterGuestMode() {
        authRepository.enterGuestMode()
    }
}