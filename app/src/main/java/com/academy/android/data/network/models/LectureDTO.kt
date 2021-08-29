package com.academy.android.data.network.models

import com.academy.android.domain.models.Lecture
import kotlinx.serialization.Serializable

@Serializable
data class LectureDTO(
    val id: Long,
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

fun LectureDTO.toLecture(): Lecture =
    Lecture(
        id = id,
        title = title,
        youtubeUrl = youtubeUrl,
        githubRepoUrl = githubRepoUrl,
        telegramChannel = telegramChannel,
        additionalMaterials = additionalMaterials.map(AdditionalMaterialDTO::toAdditionalMaterial),
        imgUrl = imgUrl,
        tags = tags,
        courseId = courseId,
        startTimestamp = startTimestamp
    )