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

    val videosList: Flow<List<VideosItemData>> = getFilteredVideosUseCase().flowOn(Dispatchers.IO).map { it.map {it.toVideosItemData()} }
}