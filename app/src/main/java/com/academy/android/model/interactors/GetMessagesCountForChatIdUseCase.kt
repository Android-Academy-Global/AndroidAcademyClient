package com.academy.android.model.interactors

import com.academy.android.data.repositories.ChatsRepositorySource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMessagesCountForChatIdUseCase @Inject constructor(
    private val chatsRepository: ChatsRepositorySource
) {
    operator fun invoke(chatId: Long): Flow<Int> =
        chatsRepository.getMessagesCountForId(chatId)
}