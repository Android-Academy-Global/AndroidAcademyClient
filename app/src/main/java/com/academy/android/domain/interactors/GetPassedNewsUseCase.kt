package com.academy.android.domain.interactors

import com.academy.android.data.repositories.NewsRepositorySource
import com.academy.android.domain.models.News
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.*
import javax.inject.Inject

class GetPassedNewsUseCase @Inject constructor(
    private val newsRepository: NewsRepositorySource
) {
    operator fun invoke(date: Date = Date()): Flow<List<News>> {
        return newsRepository.newsList.map { list ->
            list.filter { it.date.before(date) }
        }
    }
}