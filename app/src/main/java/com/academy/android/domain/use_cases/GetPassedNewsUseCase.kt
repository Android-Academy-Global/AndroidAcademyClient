package com.academy.android.domain.use_cases

import com.academy.android.domain.models.News
import com.academy.android.domain.repositories.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.Date
import javax.inject.Inject

class GetPassedNewsUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) {
    operator fun invoke(date: Date = Date()): Flow<List<News>> {
        return newsRepository.newsList.map { list ->
            list.filter { it.date.before(date) }
        }
    }
}