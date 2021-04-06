package com.academy.android.ui.videos

import androidx.lifecycle.ViewModel
import com.academy.android.data.repositories.FilterParameters
import com.academy.android.model.interactors.GetFilteredVideosUseCase
import com.academy.android.model.interactors.GetFilterParametersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class VideosViewModel @Inject constructor(
    getFilteredVideosUseCase: GetFilteredVideosUseCase,
    getFilterParametersUseCase: GetFilterParametersUseCase
) : ViewModel() {

    private val filterParameters = getFilterParametersUseCase()
    private var filterState: FilterState = filterParameters.toFilterState(0, 0, 1)
    private val filterStateFlow = MutableStateFlow(filterState)

    fun handleCityFilterUpdated(city: String) {
        filterStateFlow.value = filterStateFlow.value.copy(city = city)
    }

    fun handleLevelFilterUpdated(level: String) {
        filterStateFlow.value = filterStateFlow.value.copy(level = level)
    }

    fun handleYearFilterUpdated(year: String) {
        filterStateFlow.value = filterStateFlow.value.copy(year = year)
    }

    //Getters
    fun getFilterState() = filterState
    fun getFilterParameters() = filterParameters

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

data class FilterState(
    val city: String,
    val level: String,
    val year: String,
)

fun FilterParameters.toFilterState(cityInd: Int, levelInd: Int, yearInd: Int) = FilterState(
    city = cities[cityInd],
    level = levels[levelInd],
    year = years[yearInd]
)