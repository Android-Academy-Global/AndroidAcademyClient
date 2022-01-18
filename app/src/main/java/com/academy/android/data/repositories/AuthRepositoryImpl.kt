package com.academy.android.data.repositories

import com.academy.android.data.PrefsStorage
import com.academy.android.data.network.ServerApi
import com.academy.android.data.network.models.LoginRequestDTO
import com.academy.android.data.network.models.RegisterRequestDTO
import com.academy.android.data.network.models.UserProfileDTO
import com.academy.android.data.network.models.toUserProfile
import com.academy.android.domain.OperationResult
import com.academy.android.domain.models.UserProfile
import com.academy.android.domain.repositories.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val prefsStorage: PrefsStorage,
    private val serverApi: ServerApi
) : AuthRepository {

    override val userProfile: Flow<OperationResult<UserProfile, Throwable?>> =
        prefsStorage.loadProfile()

    override suspend fun login(
        username: String,
        password: String
    ): OperationResult<Unit, Throwable?> =
        try {
            serverApi.login(
                LoginRequestDTO(
                    username = username,
                    pwd = password
                )
            ).userProfile
                .also { profile ->
                    saveProfile(profile)
                }

            sendFcmTokenToBackend()

            OperationResult.Success(Unit)

        } catch (e: Throwable) {
            OperationResult.Error(e)
        }

    override suspend fun register(
        username: String,
        password: String,
        name: String,
        isMentor: Boolean
    ): OperationResult<Unit, Throwable?> =
        try {
            serverApi.register(
                RegisterRequestDTO(
                    username = username,
                    pwd = password,
                    name = name,
                    isMentor = isMentor
                )
            ).userProfile
                .also { profile ->
                    saveProfile(profile)
                }

            sendFcmTokenToBackend()

            OperationResult.Success(Unit)

        } catch (e: Throwable) {
            OperationResult.Error(e)
        }

    private suspend fun saveProfile(profileDto: UserProfileDTO) {
        profileDto.toUserProfile()
            .also { profile ->
                prefsStorage.saveProfile(profile = profile)
            }
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

    override suspend fun enterGuestMode() {
        prefsStorage.saveProfile(UserProfile.GUEST)
    }
}