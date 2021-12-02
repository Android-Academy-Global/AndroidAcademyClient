package com.academy.android.domain.interactors

import com.academy.android.data.repositories.VideosRepository
import com.academy.android.domain.models.FilterParameters
import javax.inject.Inject

class GetFilterParametersUseCase @Inject constructor(
    private val videosRepository: VideosRepository
) {
    suspend fun getFilterParameters(): FilterParameters =
        videosRepository.getFilterParameters()
}