package com.academy.android.domain.repositories

import com.academy.android.domain.models.UserProfile


interface ProfileRepository{
    suspend fun saveProfileData(profile: UserProfile)
}

