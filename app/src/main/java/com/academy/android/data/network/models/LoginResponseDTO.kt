package com.academy.android.data.network.models

import com.academy.android.domain.models.User
import kotlinx.serialization.Serializable

@Serializable
data class LoginResponseDTO(
    val userProfile: UserDTO,
    val token: String
)

fun LoginResponseDTO.toUser(): User =
    User(
        username = userProfile.username,
        name = userProfile.name,
        isMentor = userProfile.mentor,
        token = token
    )