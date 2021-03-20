package com.academy.android.ui.profile

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ProfileViewModel : ViewModel() {

  private val _text = MutableStateFlow("This is profile Fragment")
  val text: StateFlow<String> = _text
}