package com.academy.android.domain.use_cases

import com.academy.android.domain.repositories.ChatsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMessagesCountForChatIdUseCase @Inject constructor(
    private val chatsRepository: ChatsRepository
) {
    operator fun invoke(chatId: Long): Flow<Int> =
        chatsRepository.getMessagesCountForId(chatId)
}