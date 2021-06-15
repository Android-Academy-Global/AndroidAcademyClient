package com.academy.android.data.network.models

import kotlinx.serialization.Serializable

@Serializable
data class UserDTO(
    val username: String,
    val name: String,
    val mentor: Boolean
)
