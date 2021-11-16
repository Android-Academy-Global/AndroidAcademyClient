package com.academy.android.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Profile(
    var profPic: String? = null,
    var firstName: String? = null,
    var lastName: String? = null,
    var phoneNumber: String? = null,
    var email: String? = null,
    var status: String? = null
) {
    class Builder(
        private var profPic: String? = null,
        private var firstName: String? = null,
        private var lastName: String? = null,
        private var phoneNumber: String? = null,
        private var email: String? = null,
        private var status: String? = null
    ) {
        fun withFirstName(value: String): Builder =
            this.apply {
                firstName = value
            }

        fun withLastName(value: String): Builder =
            this.apply {
                lastName = value
            }

        fun withProfPic(value: String): Builder =
            this.apply {
                profPic = value
            }

        fun withPhoneNumber(value: String): Builder =
            this.apply {
                phoneNumber = value
            }

        fun withEmail(value: String): Builder =
            this.apply {
                email = value
            }

        fun withStatus(value: String): Builder =
            this.apply {
                status = value
            }

        fun build(): Profile =
            Profile(
                profPic = profPic,
                firstName = firstName,
                lastName = lastName,
                phoneNumber = phoneNumber,
                email = email,
                status = status
            )
    }
}
