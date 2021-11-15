package com.academy.android.domain.interactors

import com.academy.android.data.repositories.VideosRepositorySource
import com.academy.android.domain.models.FilterParameters
import javax.inject.Inject

class GetFilterParametersUseCase @Inject constructor(
    private val videosRepository: VideosRepositorySource
) {
    operator fun invoke(): FilterParameters =
        videosRepository.getFilterParameters()
}