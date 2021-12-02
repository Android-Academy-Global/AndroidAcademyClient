package com.academy.android.domain.interactors

import com.academy.android.data.repositories.VideosRepository
import com.academy.android.domain.models.City
import com.academy.android.domain.models.CourseLevel
import com.academy.android.domain.models.CourseYear
import com.academy.android.domain.models.Video
import javax.inject.Inject

class GetFilteredVideosUseCase @Inject constructor(
    private val videosRepository: VideosRepository
) {
    suspend fun getVideos(city: City?, level: CourseLevel?, year: CourseYear?): List<Video> =
        videosRepository.getVideos(city = city, level = level, year = year)
}