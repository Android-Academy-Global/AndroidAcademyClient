package com.academy.android.domain.models

data class FilterParameters(
    val cities: List<City>,
    val levels: List<CourseLevel>,
    val years: List<CourseYear>
)