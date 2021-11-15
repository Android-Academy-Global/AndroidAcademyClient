package com.academy.android.domain.interactors

import com.academy.android.data.repositories.NewsRepositorySource
import javax.inject.Inject

class NewsLikesInteractor @Inject constructor(
    private val newsRepository: NewsRepositorySource
) {
    fun getIsLikedForNewsId(id: Long): Boolean =
        newsRepository.getIsLikedState(id)


    fun updateLikedForNewsId(id: Long, isLiked: Boolean): Boolean =
        newsRepository.updateLiked(id, isLiked)
}