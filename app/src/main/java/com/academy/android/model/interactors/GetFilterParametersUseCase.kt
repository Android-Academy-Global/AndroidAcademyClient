package com.academy.android.model.interactors

import com.academy.android.data.repositories.FilterParameters
import com.academy.android.data.repositories.VideosRepositorySource
import javax.inject.Inject

class GetFilterParametersUseCase @Inject constructor(
    private val videosRepository: VideosRepositorySource
) {
    operator fun invoke(): FilterParameters =
        videosRepository.getFilterParameters()
}