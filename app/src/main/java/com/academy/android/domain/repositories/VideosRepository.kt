package com.academy.android.domain.repositories

import com.academy.android.domain.models.City
import com.academy.android.domain.models.CourseLevel
import com.academy.android.domain.models.CourseYear
import com.academy.android.domain.models.FilterParameters
import com.academy.android.domain.models.Video
import kotlinx.coroutines.flow.StateFlow

interface VideosRepository {
    val videosList: StateFlow<List<Video>>

    suspend fun getFilterParameters(): FilterParameters

    suspend fun getVideos(city: City?, level: CourseLevel?, year: CourseYear?): List<Video>
}