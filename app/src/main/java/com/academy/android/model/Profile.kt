package com.academy.android.model

import kotlinx.serialization.Serializable

@Serializable
data class Profile(
    var profPic: String? = null,
    var name: String? = null,
    var surname: String? = null,
    var phoneNumber: String? = null,
    var email: String? = null,
    var status: String? = null
)
