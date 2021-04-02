package com.academy.android.ui.videos

import androidx.lifecycle.ViewModel
import com.academy.android.model.interactors.GetFilteredVideosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class VideosViewModel @Inject constructor(
    getFilteredVideosUseCase: GetFilteredVideosUseCase,
) : ViewModel() {

    private val filterStateFlow = MutableStateFlow(hashMapOf<String, String>())
    private val filterChangedStateFlow = MutableStateFlow(false)
    fun applyFilter(filterParameters: HashMap<String, String>) {
        filterStateFlow.value = filterParameters
        filterChangedStateFlow.value = !filterChangedStateFlow.value
    }

    val videosList: Flow<List<VideosItemData>> =
        combine(
            getFilteredVideosUseCase(filterStateFlow).flowOn(Dispatchers.IO),
            filterStateFlow,
            filterChangedStateFlow
        ) { filteredVideosUseCase, _, _ ->
            filteredVideosUseCase.map { it.toVideosItemData() }
        }
}