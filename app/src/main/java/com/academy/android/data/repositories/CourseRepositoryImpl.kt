package com.academy.android.data.repositories

import com.academy.android.data.network.ServerApi
import com.academy.android.data.network.models.AdditionalMaterialDTO
import com.academy.android.data.network.models.UpdateCourseRequestDTO
import com.academy.android.data.network.models.UpdateLectureRequestDTO
import com.academy.android.data.network.models.toLecture
import com.academy.android.domain.OperationResult
import com.academy.android.domain.models.Course
import com.academy.android.domain.models.Lecture
import com.academy.android.domain.repositories.CourseRepository
import javax.inject.Inject

class CourseRepositoryImpl @Inject constructor(
    private val serverApi: ServerApi
) : CourseRepository {

    override suspend fun getAllCourses(): OperationResult<List<Course>, String?> =
        try {
            val courses = serverApi.getAllCourses().map {
                Course(
                    id = it.id,
                    title = it.title,
                    shortDescription = it.shortDescription,
                    fullDescription = it.fullDescription,
                    imgUrl = it.imgUrl,
                    isSubscribed = it.isSubscribed,
                    tags = it.tags
                )
            }
            OperationResult.Success(courses)
        } catch (e: Throwable) {
            OperationResult.Error(e.message)
        }

    override suspend fun getFavouriteCourses(username: String): OperationResult<List<Course>, String?> =
        try {
            val courses = serverApi.getFavouriteCourses().map {
                Course(
                    id = it.id,
                    title = it.title,
                    shortDescription = it.shortDescription,
                    fullDescription = it.fullDescription,
                    imgUrl = it.imgUrl,
                    isSubscribed = it.isSubscribed,
                    tags = it.tags
                )
            }.filter { it.isSubscribed } // TODO: remove filter
            OperationResult.Success(courses)
        } catch (e: Throwable) {
            OperationResult.Error(e.message)
        }

    override suspend fun updateCourse(course: Course): OperationResult<Unit, String?> =
        try {
            serverApi.updateCourse(
                UpdateCourseRequestDTO(
                    id = course.id,
                    title = course.title,
                    shortDescription = course.shortDescription,
                    fullDescription = course.fullDescription,
                    imgUrl = course.imgUrl,
                    subscribed = course.isSubscribed,
                    tags = course.tags
                )
            )
            OperationResult.Success(Unit)
        } catch (e: Throwable) {
            OperationResult.Error(e.message)
        }


    override suspend fun getAllLectures(courseId: Long): OperationResult<List<Lecture>, String?> =
        try {
            val lectures = serverApi.getAllLectures(courseId).map {
                it.toLecture()
            }
            OperationResult.Success(lectures)
        } catch (e: Throwable) {
            OperationResult.Error(e.message)
        }

    override suspend fun updateLecture(lecture: Lecture): OperationResult<Unit, String?> =
        try {
            serverApi.updateLecture(
                UpdateLectureRequestDTO(
                    id = lecture.id,
                    title = lecture.title,
                    youtubeUrl = lecture.youtubeUrl,
                    githubRepoUrl = lecture.githubRepoUrl,
                    telegramChannel = lecture.telegramChannel,
                    additionalMaterials = lecture.additionalMaterials.map {
                        AdditionalMaterialDTO(
                            topicName = it.topicName,
                            url = it.url
                        )
                    },
                    imgUrl = lecture.imgUrl,
                    tags = lecture.tags,
                    courseId = lecture.courseId,
                    startTimestamp = lecture.startTimestamp
                )
            )
            OperationResult.Success(Unit)
        } catch (e: Throwable) {
            OperationResult.Error(e.message)
        }
}