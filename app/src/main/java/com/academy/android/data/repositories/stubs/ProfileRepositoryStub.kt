package com.academy.android.data.repositories.stubs

import com.academy.android.domain.models.Profile
import com.academy.android.domain.repositories.ProfileRepository
import com.academy.android.util.SharedPreferenceHelper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class ProfileRepositoryStub @Inject constructor(
    private val prefsHelper: SharedPreferenceHelper
) : ProfileRepository {

    private val _profileData = MutableStateFlow<Profile>(getProfileData())
    override val profileData: StateFlow<Profile> = _profileData

    override fun saveProfileData(profile: Profile) {
        prefsHelper.saveProfData(profile)
            .also {
                _profileData.tryEmit(profile)
            }
    }

    override fun getProfileData(): Profile {
        return prefsHelper.getProfData()
    }
}