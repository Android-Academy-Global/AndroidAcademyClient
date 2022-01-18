package com.academy.android.ui.videos

import com.academy.android.domain.models.City
import com.academy.android.domain.models.CourseLevel
import com.academy.android.domain.models.CourseYear
import com.academy.android.domain.models.Video

data class VideosItemData(
    val id: Long = 0,
    val name: String = "",
    val date: String = "",
    val picture: String = "",
    val city: City,
    val level: CourseLevel,
    val year: CourseYear = CourseYear("")
)

fun Video.toVideosItemData() = VideosItemData(
    id = id,
    name = name,
    date = date,
    picture = picture,
    city = city,
    level = level,
    year = year
)