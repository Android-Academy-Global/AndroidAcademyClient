package com.academy.android.domain.interactors

import com.academy.android.data.repositories.VideosRepositorySource
import com.academy.android.domain.models.Video
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetFilteredVideosUseCase @Inject constructor(
    private val videosRepository: VideosRepositorySource
) {
    operator fun invoke(city: String, level: String, year: String): Flow<List<Video>> =
        videosRepository.videosList.map { list ->
            list.filter {
                it.city == city &&
                it.level == level &&
                it.year == year
            }
        }
}