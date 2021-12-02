package com.academy.android.ui.videos

import com.academy.android.domain.models.City
import com.academy.android.domain.models.CourseLevel
import com.academy.android.domain.models.CourseYear
import com.academy.android.domain.models.Video
import com.academy.android.ui.base.RvItemData

data class VideosItemData(
    override val id: Long = 0,
    val name: String = "",
    val date: String = "",
    val picture: String = "",
    val city: City,
    val level: CourseLevel,
    val year: CourseYear = CourseYear("")
) : RvItemData

fun Video.toVideosItemData() = VideosItemData(
    id = id,
    name = name,
    date = date,
    picture = picture,
    city = city,
    level = level,
    year = year
)