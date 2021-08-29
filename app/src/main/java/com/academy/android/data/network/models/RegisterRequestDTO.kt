package com.academy.android.data.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RegisterRequestDTO(
    val username: String,
    val pwd: String,
    val name: String,
    @SerialName("mentor") val isMentor: Boolean
)