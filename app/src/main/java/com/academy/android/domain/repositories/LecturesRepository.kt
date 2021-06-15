package com.academy.android.domain.repositories

import com.academy.android.domain.models.Lecture


interface LecturesRepository {
    suspend fun getLecture(id: Long): Lecture
}