package com.academy.android.data.repositories

import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.academy.android.data.PrefsStorage
import com.academy.android.data.network.ServerApi
import com.academy.android.data.network.models.LoginRequestDTO
import com.academy.android.data.network.models.RegisterRequestDTO
import com.academy.android.data.network.models.toUser
import com.academy.android.domain.OperationResult
import com.academy.android.domain.models.AuthState
import com.academy.android.domain.models.User
import com.academy.android.domain.repositories.AuthRepository
import com.academy.android.domain.repositories.MyOptional
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

// fixme: handle auth properly
class AuthRepositoryImpl @Inject constructor(
    private val prefsStorage: PrefsStorage,
    private val serverApi: ServerApi
) : AuthRepository {
    override val authState: Flow<AuthState> =
        prefsStorage.readString(AUTH_STATE_PREFS_KEY, AuthState.UNAUTHORIZED.name)
            .map { authStateString -> AuthState.valueOf(authStateString) }

    override suspend fun changeAuthState(newAuthState: AuthState) {
        prefsStorage.writeString(
            AUTH_STATE_PREFS_KEY, newAuthState.name
        )
    }

    override fun loadUser(): User? {
//        prefsStorage.loadUser()
        return null
    }

    override fun observeUser(): LiveData<MyOptional<User>>  {
//        prefsStorage.observeUser()
        return MutableLiveData()
    }

    override suspend fun login(
        username: String,
        password: String
    ): OperationResult<Unit, String?> =
        try {
            val user = serverApi.login(
                LoginRequestDTO(
                    username = username,
                    pwd = password
                )
            ).toUser()

//            prefsStorage.saveToSharedPref(user)
            sendFcmTokenToBackend()

            OperationResult.Success(Unit)

        } catch (e: Exception) {
            OperationResult.Error(e.message)
        }


    override suspend fun register(
        username: String,
        password: String,
        name: String,
        isMentor: Boolean
    ): OperationResult<Unit, String?> =
        try {
            val user = serverApi.register(
                RegisterRequestDTO(
                    username = username,
                    pwd = password,
                    name = name,
                    isMentor = isMentor
                )
            ).toUser()

//            prefsStorage.saveToSharedPref(user)
            sendFcmTokenToBackend()

            OperationResult.Success(Unit)

        } catch (e: Exception) {
            OperationResult.Error(e.message)
        }

    private fun sendFcmTokenToBackend() {
//        FirebaseMessaging.getInstance().token
//            .addOnSuccessListener { fcmToken ->
//                GlobalScope.launch {
//                    println("fcm_token:$fcmToken")
//                    serverApi.updateFcmToken(fcmToken)
//                }
//            }
//            .addOnFailureListener {
//                Timber.e(it, "Cannot get FCM token")
//            }
    }

    override fun logOut() {
//        prefsStorage.saveToSharedPref(null)
    }

    companion object {
        private val AUTH_STATE_PREFS_KEY = stringPreferencesKey("auth_state")
    }
}

