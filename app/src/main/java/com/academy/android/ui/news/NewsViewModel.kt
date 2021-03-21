package com.academy.android.ui.news

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(

) : ViewModel() {

  private val _text = MutableStateFlow("This is news Fragment")
  val text: StateFlow<String> = _text
}