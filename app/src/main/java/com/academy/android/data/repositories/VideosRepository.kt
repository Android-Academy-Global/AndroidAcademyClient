package com.academy.android.data.repositories

import com.academy.android.model.Video
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

interface VideosRepositorySource {
    val videosList: StateFlow<List<Video>>
    var filterState: FilterState
    val cities: List<String>
    val levels: List<String>
    val years: List<String>
}

class VideosRepository @Inject constructor() : VideosRepositorySource{
    private val _videosList = MutableStateFlow(provideMokkVideos())
    override val videosList: StateFlow<List<Video>> = _videosList

    override val cities = listOf("Moscow", "Minsk", "Tel-Aviv")
    override val levels = listOf("Fundamentals", "Advanced")
    override val years = listOf("2019-2020", "2020-2021")
    override var filterState = FilterState()

    private fun provideMokkVideos(): List<Video> = listOf(
        Video(
            1,
            "Android Fundamentals #0: How to Kotlin (Russian language)",
            "27 oct. 2020",
        "https://img.youtube.com/vi/_clrkv6VL4g/0.jpg",
            "Moscow",
            "Fundamentals",
            "2020-2021"
        ),
        Video(
            2,
            "Coroutines, Yonatan Levin - Android Developer@Monday",
            "7 feb. 2020",
            "https://img.youtube.com/vi/ngGI-zVJceo/0.jpg",
            "Minsk",
            "Advanced",
            "2019-2020"
        ),
        Video(
            3,
            "Android Academy TLV 2019 - Fundamentals Course - fragments by Gil Goldzweig",
            "25 dec. 2019",
            "https://img.youtube.com/vi/3zb7AnbFfTA/0.jpg",
            "Tel-Aviv",
            "Fundamentals",
            "2019-2020"
        )
    )

}

data class FilterState(
    val city: String = "Moscow",
    val level: String = "Fundamentals",
    val year: String = "2020-2021",
)