package com.academy.android.domain.repositories

import kotlinx.coroutines.flow.Flow


interface ChatsRepository {
    fun getMessagesCountForId(chatId: Long): Flow<Int>
}