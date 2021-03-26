package com.academy.android.model.interactors

import com.academy.android.data.repositories.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLikesCountForChatIdUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) {
    operator fun invoke(chatId: Long): Flow<Int> =
        newsRepository.getLikesCountForId(chatId)
}