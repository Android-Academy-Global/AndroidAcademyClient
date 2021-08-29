package com.academy.android.data.network.models

import kotlinx.serialization.Serializable

@Serializable
data class UpdateCourseRequestDTO(
    val id: Long? = null,
    val title: String,
    val shortDescription: String? = null,
    val fullDescription: String? = null,
    val imgUrl: String? = null,
    val tags: List<String>,
    val subscribed: Boolean
)