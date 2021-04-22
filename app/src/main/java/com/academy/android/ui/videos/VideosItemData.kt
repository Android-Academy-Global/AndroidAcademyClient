package com.academy.android.ui.videos

import com.academy.android.model.Video
import com.academy.android.ui.base.RvItemData

data class VideosItemData(
    override val id: Long = 0,
    val name: String = "",
    val date: String = "",
    val picture: String = "",
    val city: String = "",
    val level: String = "",
    val year: String = ""
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