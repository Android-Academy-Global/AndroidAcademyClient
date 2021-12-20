package com.academy.android.domain.repositories

import androidx.lifecycle.LiveData
import com.academy.android.domain.OperationResult
import com.academy.android.domain.models.AuthState
import com.academy.android.domain.models.User
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    val authState: Flow<AuthState>

    suspend fun changeAuthState(newAuthState: AuthState)

    fun loadUser(): User?

    suspend fun login(username: String, password: String): OperationResult<Unit, String?>

    suspend fun register(
        username: String,
        password: String,
        name: String,
        isMentor: Boolean
    ): OperationResult<Unit, String?>

    fun observeUser(): LiveData<MyOptional<User>>

    fun logOut()

}

data class MyOptional<T : Any>(
    val value: T?
)