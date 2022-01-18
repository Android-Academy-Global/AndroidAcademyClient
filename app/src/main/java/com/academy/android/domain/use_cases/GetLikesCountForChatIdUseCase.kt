package com.academy.android.domain.use_cases

import com.academy.android.domain.repositories.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLikesCountForChatIdUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) {
    operator fun invoke(chatId: Long): Flow<Int> =
        newsRepository.getLikesCountForId(chatId)
}