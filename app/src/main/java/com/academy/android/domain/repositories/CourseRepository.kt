package com.academy.android.domain.repositories

import com.academy.android.domain.OperationResult
import com.academy.android.domain.models.Course
import com.academy.android.domain.models.Lecture


interface CourseRepository {

    suspend fun getAllCourses(): OperationResult<List<Course>, String?>

    suspend fun getFavouriteCourses(username: String): OperationResult<List<Course>, String?>

    suspend fun updateCourse(course: Course) : OperationResult<Unit, String?>

    suspend fun getAllLectures(courseId: Long) : OperationResult<List<Lecture>, String?>

    suspend fun updateLecture(lecture: Lecture) : OperationResult<Unit, String?>
}