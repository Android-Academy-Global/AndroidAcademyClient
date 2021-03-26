package com.academy.android.ui.news

import androidx.lifecycle.ViewModel
import com.academy.android.model.interactors.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    getFeaturedNewsUseCase: GetFeaturedNewsUseCase,
    getPassedNewsUseCase: GetPassedNewsUseCase,
    private val getMessagesCountForChatIdUseCase: GetMessagesCountForChatIdUseCase,
    private val getLikesCountForChatIdUseCase: GetLikesCountForChatIdUseCase,
    private val newsLikesInteractor: NewsLikesInteractor,
) : ViewModel() {

    private val filterState = MutableStateFlow(true)

    val newsList: Flow<List<NewsItemData>> =
        combine(
            getFeaturedNewsUseCase().flowOn(Dispatchers.IO),
            getPassedNewsUseCase().flowOn(Dispatchers.IO),
            filterState
        ) { featuredNews, passedNews, filterState ->
            if (filterState) featuredNews.map { it.toNewsItemData() }
            else passedNews.map { it.toNewsItemData() }
        }

    fun getIsLiked(id: Long): Boolean =
        newsLikesInteractor.getIsLikedForNewsId(id)

    fun handleLike(id: Long, isLiked: Boolean): Boolean =
        newsLikesInteractor.updateLikedForNewsId(id, isLiked)

    fun getChatMessagesCount(id: Long): Flow<Int> =
        getMessagesCountForChatIdUseCase(id).flowOn(Dispatchers.IO)

    fun getLikesCount(id: Long): Flow<Int> =
        getLikesCountForChatIdUseCase(id).flowOn(Dispatchers.IO)

    fun applyFilterNew() {
        filterState.value = true
    }

    fun applyFilterOld() {
        filterState.value = false
    }
}