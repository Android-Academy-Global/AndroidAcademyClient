package com.academy.android.domain.repositories

import com.academy.android.domain.models.Profile
import kotlinx.coroutines.flow.StateFlow


interface ProfileRepository{
    val profileData: StateFlow<Profile>

    suspend fun saveProfileData(
        profile: Profile
    )

    suspend fun getProfileData(): Profile
}

