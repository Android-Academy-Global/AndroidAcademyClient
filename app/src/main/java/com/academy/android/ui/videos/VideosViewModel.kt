package com.academy.android.ui.videos

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class VideosViewModel : ViewModel() {

  private val _text = MutableStateFlow("This is videos Fragment")
  val text: StateFlow<String> = _text
}