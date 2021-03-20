package com.academy.android.ui.news

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class NewsViewModel : ViewModel() {

  private val _text = MutableStateFlow("This is news Fragment")
  val text: StateFlow<String> = _text
}