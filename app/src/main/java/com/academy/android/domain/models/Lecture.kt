package com.academy.android.domain.models

data class Lecture(
    val id: Long? = null,
    val title: String,
    val youtubeUrl: String = "",
    val githubRepoUrl: String = "",
    val telegramChannel: String = "",
    val additionalMaterials: List<AdditionalMaterial>,
    val imgUrl: String? = null,
    val tags: List<String>,
    val courseId: Long,
    val startTimestamp : Long
)