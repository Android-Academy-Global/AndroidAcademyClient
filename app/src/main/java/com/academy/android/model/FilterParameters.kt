package com.academy.android.model

import com.academy.android.ui.videos.FilterState

data class FilterParameters(
    val cities: List<String>,
    val levels: List<String>,
    val years: List<String>
)