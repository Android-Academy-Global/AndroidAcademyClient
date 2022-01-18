package com.academy.android.data.network.models

import com.academy.android.domain.models.AdditionalMaterial
import kotlinx.serialization.Serializable

@Serializable
data class AdditionalMaterialDTO(
    val topicName: String,
    val url: String
)

fun AdditionalMaterialDTO.toAdditionalMaterial(): AdditionalMaterial =
    AdditionalMaterial(topicName = topicName, url = url)