package com.academy.android.ui.news

import com.academy.android.model.News
import com.academy.android.ui.base.RvItemData
import java.util.*

data class NewsItemData(
    override val id: Long = 0,
    val text: String = "",
    val link: String = "",
    val picture: String = "",
    val date: Date = Date(),
    val chatId: Long = 0,
) : RvItemData

fun News.toNewsItemData() = NewsItemData(
    id = id,
    text = text,
    link = link,
    picture = picture,
    date = date,
    chatId = chatId,
)