package com.academy.android.data.network.models

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequestDTO(
    val username: String,
    val pwd: String
)