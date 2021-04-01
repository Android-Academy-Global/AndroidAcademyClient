package com.academy.android.model.interactors

import com.academy.android.data.repositories.VideosRepositorySource
import com.academy.android.model.Videos
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetFilteredVideosUseCase @Inject constructor(
    private val videosRepository: VideosRepositorySource
) {
    operator fun invoke(city: String = "",
                        level: String = "",
                        date: String = ""): Flow<List<Videos>> =
        videosRepository.videosList.map { list ->
            list.filter { it.city == city && it.level == level && it.date == date }
        }
}