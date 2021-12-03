package com.academy.android.data.repositories.stubs

import com.academy.android.domain.models.City
import com.academy.android.domain.models.CourseLevel
import com.academy.android.domain.models.CourseYear
import com.academy.android.domain.models.FilterParameters
import com.academy.android.domain.models.Video
import com.academy.android.domain.repositories.VideosRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class VideosRepositoryStub @Inject constructor() : VideosRepository {
    private val _videosList = MutableStateFlow(provideMokkVideos())
    override val videosList: StateFlow<List<Video>> = _videosList

    private val filterParameters = FilterParameters(
        listOf(City.MOSCOW, City.MINSK, City.TEL_AVIV),
        listOf(CourseLevel.FUNDAMENTALS, CourseLevel.ADVANCED),
        listOf(
            CourseYear("2019-2020"),
            CourseYear("2020-2021")
        )
    )

    override suspend fun getFilterParameters(): FilterParameters = filterParameters

    override suspend fun getVideos(
        city: City?,
        level: CourseLevel?,
        year: CourseYear?
    ): List<Video> =
        provideMokkVideos()
            .filter { video ->
                (city == null || video.city == city) &&
                        (level == null || video.level == level) &&
                        (year == null || video.year == year)
            }

    private fun provideMokkVideos(): List<Video> = listOf(
        Video(
            1,
            "Android Fundamentals #0: How to Kotlin (Russian language)",
            "27 oct. 2020",
            "https://img.youtube.com/vi/_clrkv6VL4g/0.jpg",
            City.MOSCOW,
            CourseLevel.FUNDAMENTALS,
            CourseYear("2020-2021")
        ),
        Video(
            2,
            "Coroutines, Yonatan Levin - Android Developer@Monday",
            "7 feb. 2020",
            "https://img.youtube.com/vi/ngGI-zVJceo/0.jpg",
            City.MINSK,
            CourseLevel.ADVANCED,
            CourseYear("2019-2020")
        ),
        Video(
            3,
            "Android Academy TLV 2019 - Fundamentals Course - fragments by Gil Goldzweig",
            "25 dec. 2019",
            "https://img.youtube.com/vi/3zb7AnbFfTA/0.jpg",
            City.TEL_AVIV,
            CourseLevel.FUNDAMENTALS,
            CourseYear("2019-2020")
        )
    )
}