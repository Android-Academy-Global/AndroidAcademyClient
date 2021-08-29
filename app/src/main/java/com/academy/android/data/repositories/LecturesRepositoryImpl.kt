package com.academy.android.data.repositories

import com.academy.android.data.network.ServerApi
import com.academy.android.data.network.models.toLecture
import com.academy.android.domain.models.Lecture
import com.academy.android.domain.repositories.LecturesRepository
import javax.inject.Inject

class LecturesRepositoryImpl @Inject constructor(
    private val serverApi: ServerApi
) : LecturesRepository {

    override suspend fun getLecture(id: Long): Lecture =
        serverApi.getLectureById(lectureId = id)
            .toLecture()
}