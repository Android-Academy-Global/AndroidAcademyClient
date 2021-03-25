package com.academy.android.model.interactors

import com.academy.android.data.repositories.NewsRepository
import com.academy.android.model.News
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.*
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