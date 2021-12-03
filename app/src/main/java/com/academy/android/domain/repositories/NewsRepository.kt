package com.academy.android.domain.repositories

import com.academy.android.domain.models.News
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface NewsRepository {
    val newsList: StateFlow<List<News>>
    fun updateLiked(id: Long, isLiked: Boolean): Boolean
    fun getIsLikedState(id: Long): Boolean
    fun getLikesCountForId(chatId: Long): Flow<Int>
}