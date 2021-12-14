package com.academy.android.ui.news

import com.academy.android.domain.models.News
import java.util.Date

data class NewsItemData(
    val id: Long = 0,
    val text: String = "",
    val link: String = "",
    val picture: String = "",
    val date: Date = Date(),
    val chatId: Long = 0,
    val likesCount: Int = 0,
    val commentsCount: Int = 0
)

fun News.toNewsItemData() = NewsItemData(
    id = id,
    text = text,
    link = link,
    picture = picture,
    date = date,
    chatId = chatId,
    likesCount = likesCount,
    commentsCount = commentsCount
)