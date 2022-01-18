package com.academy.android.data.network

import com.academy.android.data.network.models.CourseDTO
import com.academy.android.data.network.models.LectureDTO
import com.academy.android.data.network.models.LoginRequestDTO
import com.academy.android.data.network.models.LoginResponseDTO
import com.academy.android.data.network.models.RegisterRequestDTO
import com.academy.android.data.network.models.UpdateCourseRequestDTO
import com.academy.android.data.network.models.UpdateLectureRequestDTO
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ServerApi {
    @POST("login")
    suspend fun login(
        @Body loginRequest: LoginRequestDTO
    ): LoginResponseDTO

    @POST("register")
    suspend fun register(
        @Body registerRequestDTO: RegisterRequestDTO
    ): LoginResponseDTO

    @GET("courses/favorite")
    suspend fun getFavouriteCourses(): List<CourseDTO>

    @GET("courses/all")
    suspend fun getAllCourses(): List<CourseDTO>

    @POST("courses/update")
    suspend fun updateCourse(
        @Body updateCourseRequestDTO: UpdateCourseRequestDTO
    )

    @POST("lectures/update")
    suspend fun updateLecture(
        @Body updateLectureRequestDTO: UpdateLectureRequestDTO
    )

    @GET("lectures/all")
    suspend fun getAllLectures(
        @Query(value = "courseId") courseId: Long
    ): List<LectureDTO>

    @GET("lectures/by-id")
    suspend fun getLectureById(
        @Query(value = "lectureId") lectureId: Long
    ): LectureDTO

    @POST("user/update-fcm-token")
    suspend fun updateFcmToken(
        @Body fcmToken: String
    )
}