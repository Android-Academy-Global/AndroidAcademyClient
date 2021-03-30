package com.academy.android.model

import java.util.*

data class News(
    val id: Long = 0,
    val text: String = "",
    val link: String = "",
    val picture: String = "",
    val date: Date = Date(),
    val chatId: Long = id,
)