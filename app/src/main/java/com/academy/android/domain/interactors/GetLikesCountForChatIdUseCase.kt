package com.academy.android.domain.interactors

import com.academy.android.data.repositories.NewsRepositorySource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLikesCountForChatIdUseCase @Inject constructor(
    private val newsRepository: NewsRepositorySource
) {
    operator fun invoke(chatId: Long): Flow<Int> =
        newsRepository.getLikesCountForId(chatId)
}