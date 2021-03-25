package com.academy.android.model.interactors

import com.academy.android.data.repositories.ChatsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMessagesCountForChatIdUseCase @Inject constructor(
    private val chatsRepository: ChatsRepository
) {
    operator fun invoke(chatId: Long): Flow<Int> =
        chatsRepository.getMessagesCountForId(chatId)
}