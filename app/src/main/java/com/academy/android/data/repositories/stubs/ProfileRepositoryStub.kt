package com.academy.android.data.repositories.stubs

import com.academy.android.data.PrefsStorage
import com.academy.android.domain.models.Profile
import com.academy.android.domain.repositories.ProfileRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class ProfileRepositoryStub @Inject constructor(
    private val prefsStorage: PrefsStorage
) : ProfileRepository {

    private val _profileData = MutableStateFlow(Profile())
    override val profileData: StateFlow<Profile> = _profileData

    override suspend fun saveProfileData(profile: Profile) {
        prefsStorage.saveProfData(profile)
            .also {
                _profileData.tryEmit(profile)
            }
    }

    override suspend fun getProfileData(): Profile =
        prefsStorage.getProfData()
}