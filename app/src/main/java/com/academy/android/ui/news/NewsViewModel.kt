package com.academy.android.ui.news

import androidx.lifecycle.ViewModel
import com.academy.android.model.interactors.GetFeaturedNewsUseCase
import com.academy.android.model.interactors.GetMessagesCountForChatIdUseCase
import com.academy.android.model.interactors.GetPassedNewsUseCase
import com.academy.android.model.interactors.NewsLikesInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    getFeaturedNewsUseCase: GetFeaturedNewsUseCase,
    getPassedNewsUseCase: GetPassedNewsUseCase,
    private val getMessagesCountForChatIdUseCase: GetMessagesCountForChatIdUseCase,
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


    fun applyFilterNew() {
        filterState.value = true
    }

    fun applyFilterOld() {
        filterState.value = false
    }
}