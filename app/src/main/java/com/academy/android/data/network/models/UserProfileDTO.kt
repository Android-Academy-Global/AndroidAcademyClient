package com.academy.android.data.network.models

import com.academy.android.domain.models.UserProfile
import com.academy.android.domain.models.UserTitle
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserProfileDTO(
    @SerialName("username")
    val username: String,
    @SerialName("mentor")
    val mentor: Boolean,
    @SerialName("prof_pic")
    var profPic: String? = null,
    @SerialName("first_name")
    var firstName: String? = null,
    @SerialName("last_name")
    var lastName: String? = null,
    @SerialName("phone_number")
    var phoneNumber: String? = null,
    @SerialName("email")
    var email: String? = null,
    @SerialName("title")
    var title: String? = null
)

fun UserProfileDTO.toUserProfile(): UserProfile =
    UserProfile(
        profPic = profPic,
        firstName = firstName,
        lastName = lastName,
        phoneNumber = phoneNumber,
        email = email,
        title = title?.let { userTitle -> UserTitle.valueOf(userTitle) } ?: UserTitle.UNKNOWN
    )