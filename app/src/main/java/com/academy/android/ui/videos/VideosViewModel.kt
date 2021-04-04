package com.academy.android.ui.videos

import androidx.lifecycle.ViewModel
import com.academy.android.data.repositories.FilterState
import com.academy.android.data.repositories.VideosRepositorySource
import com.academy.android.model.interactors.GetFilteredVideosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class VideosViewModel @Inject constructor(
    getFilteredVideosUseCase: GetFilteredVideosUseCase,
    videosRepository: VideosRepositorySource
) : ViewModel() {

    private val filterStateFlow = MutableStateFlow(videosRepository.filterState)
    val cities = videosRepository.cities
    val levels = videosRepository.levels
    val years = videosRepository.years

    fun updateFilterState(city: String, level: String, year: String) {
        filterStateFlow.value = filterStateFlow.value.copy(city, level, year)
    }

    fun getFilterState() : FilterState = filterStateFlow.value

    val videosList: Flow<List<VideosItemData>> =
        filterStateFlow
            .flatMapLatest { filterState ->
                getFilteredVideosUseCase(filterState.city, filterState.level, filterState.year)
                    .flowOn(Dispatchers.IO)
                    .map { listOfVideos ->
                        listOfVideos.map { it.toVideosItemData() }
                    }
            }

}