package com.academy.android.data.network.models

import com.academy.android.domain.models.Course
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CourseDTO(
    val id: Long,
    val title: String,
    val shortDescription: String? = null,
    val fullDescription: String? = null,
    val imgUrl: String? = null,
    val tags: List<String>,
    @SerialName("subscribed") val isSubscribed: Boolean = false
)

fun CourseDTO.toCourse(): Course =
    Course(
        id = id,
        title = title,
        shortDescription = shortDescription,
        fullDescription = fullDescription,
        imgUrl = imgUrl,
        tags = tags,
        isSubscribed = isSubscribed
    )