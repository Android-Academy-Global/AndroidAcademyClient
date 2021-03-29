package com.academy.android.model.interactors

import com.academy.android.data.repositories.NewsRepositorySource
import com.academy.android.model.News
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.*
import javax.inject.Inject

class GetFeaturedNewsUseCase @Inject constructor(
    private val newsRepository: NewsRepositorySource
) {
    operator fun invoke(date: Date = Date()): Flow<List<News>> =
        newsRepository.newsList.map { list ->
            list.filter { it.date.after(date) }
        }
}