package com.academy.android.domain.use_cases

import com.academy.android.domain.models.City
import com.academy.android.domain.models.CourseLevel
import com.academy.android.domain.models.CourseYear
import com.academy.android.domain.models.Video
import com.academy.android.domain.repositories.VideosRepository
import javax.inject.Inject

class GetFilteredVideosUseCase @Inject constructor(
    private val videosRepository: VideosRepository
) {
    suspend fun getVideos(city: City?, level: CourseLevel?, year: CourseYear?): List<Video> =
        videosRepository.getVideos(city = city, level = level, year = year)
}