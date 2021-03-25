package com.academy.android.data.repositories

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ChatsRepository @Inject constructor(

) {
    /**
    * Mokk function to emulate flow of messages (buggy)
    */
    fun getMessagesCountForId(chatId: Long): Flow<Int> = flow {
        var numberOfMessages = chatId.toInt()
        repeat(10_000) {
            kotlinx.coroutines.delay(1000)
            val newMessages = (0..10).random()
            if (newMessages < 5 && newMessages != 0) {
                numberOfMessages += newMessages
                emit(numberOfMessages)
            }
        }
    }
}