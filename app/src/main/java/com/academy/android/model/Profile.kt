package com.academy.android.model

import kotlinx.serialization.Serializable

@Serializable
data class Profile(
    var profPic: String? = null,
    var firstName: String? = null,
    var lastName: String? = null,
    var phoneNumber: String? = null,
    var email: String? = null,
    var status: String? = null
)
