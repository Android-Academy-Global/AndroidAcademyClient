package com.academy.android.data.repositories

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ChatsRepository @Inject constructor(

) {
    init {
        generateMessagesCount()
    }

    private val messagesCountState = MutableStateFlow<Map<Long, Int>>(emptyMap())

    fun getMessagesCountForId(chatId: Long): Flow<Int> =
        messagesCountState.filter { it.containsKey(chatId) }.map { it[chatId] ?: 0 }

    private fun generateMessagesCount() {
        val messagesCount = mutableMapOf<Long, Int>()
        CoroutineScope(Dispatchers.IO).launch {
            repeat(10_000) {
                val newMessagesCount = mutableMapOf<Long, Int>()
                (1L..8L).forEach { i ->
                    val newMessages = (0..10).random()
                    if (newMessages < 5 && newMessages != 0) {
                        newMessagesCount[i] = newMessages + (messagesCount[i] ?: 0)
                    }
                }
                messagesCountState.value = newMessagesCount
                messagesCount.putAll(newMessagesCount)
                delay(1000)
            }
        }
    }
}