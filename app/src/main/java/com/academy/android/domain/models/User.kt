package com.academy.android.domain.models

data class User(
    val username : String,
    val name : String,
    val isMentor : Boolean,
    val token : String
)