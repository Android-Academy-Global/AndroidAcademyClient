package com.academy.android.data.network.models

import kotlinx.serialization.Serializable

@Serializable
data class UpdateLectureRequestDTO(
    val id: Long? = null,
    val title: String,
    val youtubeUrl: String = "",
    val githubRepoUrl: String = "",
    val telegramChannel: String = "",
    val additionalMaterials: List<AdditionalMaterialDTO>,
    val imgUrl: String? = null,
    val tags: List<String>,
    val courseId: Long,
    val startTimestamp : Long
)
