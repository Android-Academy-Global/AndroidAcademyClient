package com.academy.android.ui.videos

import androidx.lifecycle.ViewModel
import com.academy.android.domain.interactors.GetFilterParametersUseCase
import com.academy.android.domain.interactors.GetFilteredVideosUseCase
import com.academy.android.domain.models.FilterParameters
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class VideosViewModel @Inject constructor(
    getFilteredVideosUseCase: GetFilteredVideosUseCase,
    getFilterParametersUseCase: GetFilterParametersUseCase
) : ViewModel() {

    val filterParameters = getFilterParametersUseCase()
    val initialFilterState: FilterState = filterParameters.toFilterState(0, 0, 1)
    private val filterStateFlow = MutableStateFlow(initialFilterState)

    fun handleCityFilterUpdated(city: String) {
        filterStateFlow.value = filterStateFlow.value.copy(city = city)
    }

    fun handleLevelFilterUpdated(level: String) {
        filterStateFlow.value = filterStateFlow.value.copy(level = level)
    }

    fun handleYearFilterUpdated(year: String) {
        filterStateFlow.value = filterStateFlow.value.copy(year = year)
    }


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

private fun FilterParameters.toFilterState(cityInd: Int, levelInd: Int, yearInd: Int) = FilterState(
    city = cities[cityInd],
    level = levels[levelInd],
    year = years[yearInd]
)