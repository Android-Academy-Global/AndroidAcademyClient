package com.academy.android.domain.use_cases

import com.academy.android.domain.repositories.NewsRepository
import javax.inject.Inject

class NewsLikesUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) {
    fun getIsLikedForNewsId(id: Long): Boolean =
        newsRepository.getIsLikedState(id)


    fun updateLikedForNewsId(id: Long, isLiked: Boolean): Boolean =
        newsRepository.updateLiked(id, isLiked)
}