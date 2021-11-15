package com.academy.android.domain.models

data class FilterParameters(
    val cities: List<String>,
    val levels: List<String>,
    val years: List<String>
)