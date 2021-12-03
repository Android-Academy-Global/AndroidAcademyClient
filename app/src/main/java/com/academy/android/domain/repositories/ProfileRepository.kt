package com.academy.android.domain.repositories

import com.academy.android.domain.models.Profile
import kotlinx.coroutines.flow.StateFlow


interface ProfileRepository{
    val profileData: StateFlow<Profile>

    fun saveProfileData(
        profile: Profile
    )

    fun getProfileData(): Profile
}

