package com.academy.android.data.repositories.stubs

import com.academy.android.data.PrefsStorage
import com.academy.android.domain.models.UserProfile
import com.academy.android.domain.repositories.ProfileRepository
import javax.inject.Inject

class ProfileRepositoryStub @Inject constructor(
    private val prefsStorage: PrefsStorage
) : ProfileRepository {

    override suspend fun saveProfileData(profile: UserProfile) {
        prefsStorage.saveProfile(profile)
    }
}