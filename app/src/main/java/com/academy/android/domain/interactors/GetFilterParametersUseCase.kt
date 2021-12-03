package com.academy.android.domain.interactors

import com.academy.android.domain.models.FilterParameters
import com.academy.android.domain.repositories.VideosRepository
import javax.inject.Inject

class GetFilterParametersUseCase @Inject constructor(
    private val videosRepository: VideosRepository
) {
    suspend fun getFilterParameters(): FilterParameters =
        videosRepository.getFilterParameters()
}