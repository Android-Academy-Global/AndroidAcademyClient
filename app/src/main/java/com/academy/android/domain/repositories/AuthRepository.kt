package com.academy.android.domain.repositories

import com.academy.android.domain.OperationResult
import com.academy.android.domain.models.UserProfile
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    val userProfile: Flow<OperationResult<UserProfile, Throwable?>>

    suspend fun login(username: String, password: String): OperationResult<Unit, Throwable?>

    suspend fun register(
        username: String,
        password: String,
        name: String,
        isMentor: Boolean
    ): OperationResult<Unit, Throwable?>

    suspend fun enterGuestMode()

}