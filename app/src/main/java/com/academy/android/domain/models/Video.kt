package com.academy.android.domain.models

data class Video(
    val id: Long = 0,
    val name: String = "",
    val date: String = "",
    val picture: String = "",
    val city: City = City.UNKNOWN,
    val level: CourseLevel = CourseLevel.UNKNOWN,
    val year: CourseYear = CourseYear("")
)