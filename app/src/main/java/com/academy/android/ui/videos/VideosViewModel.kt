package com.academy.android.ui.videos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.academy.android.domain.models.City
import com.academy.android.domain.models.CourseLevel
import com.academy.android.domain.models.CourseYear
import com.academy.android.domain.models.FilterParameters
import com.academy.android.domain.models.Video
import com.academy.android.domain.use_cases.GetFilterParametersUseCase
import com.academy.android.domain.use_cases.GetFilteredVideosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VideosViewModel @Inject constructor(
    getFilteredVideosUseCase: GetFilteredVideosUseCase,
    getFilterParametersUseCase: GetFilterParametersUseCase
) : ViewModel() {

    private val _videosList = MutableStateFlow<List<VideosItemData>>(emptyList())
    val videosList: StateFlow<List<VideosItemData>> = _videosList

    private val _filterParameters = MutableStateFlow(FilterParameters(cities = emptyList(), levels = emptyList(), years = emptyList()))
    val filterParameters = _filterParameters

    private val _filterState = MutableStateFlow(FilterState())
    val filterState: StateFlow<FilterState> = _filterState

    init {
        viewModelScope.launch {
            _filterParameters.emit(getFilterParametersUseCase.getFilterParameters())
        }
        viewModelScope.launch {
            _filterState.collect { filter ->
                _videosList.emit(
                    getFilteredVideosUseCase.getVideos(
                        city = filter.city,
                        level = filter.level,
                        year = filter.year
                    ).map(Video::toVideosItemData)
                )
            }
        }
    }

    fun handleCityFilterUpdated(city: City?) {
        _filterState.value = _filterState.value.copy(city = city)
    }

    fun handleLevelFilterUpdated(level: CourseLevel?) {
        _filterState.value = _filterState.value.copy(level = level)
    }

    fun handleYearFilterUpdated(year: CourseYear?) {
        _filterState.value = _filterState.value.copy(year = year)
    }
}


data class FilterState(
    val city: City? = null,
    val level: CourseLevel? = null,
    val year: CourseYear? = null,
)

private fun FilterParameters.toFilterState(cityInd: Int, levelInd: Int, yearInd: Int) = FilterState(
    city = cities[cityInd],
    level = levels[levelInd],
    year = years[yearInd]
)