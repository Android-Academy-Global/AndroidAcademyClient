package com.academy.android.data.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginResponseDTO(
    @SerialName("user_profile")
    val userProfile: UserProfileDTO,
    @SerialName("token")
    val token: String
)