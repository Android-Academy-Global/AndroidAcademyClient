package com.academy.android.data.repositories

import com.academy.android.domain.models.Profile
import com.academy.android.util.SharedPreferenceHelper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject


interface ProfileRepositorySource{

    fun saveProfileData(
        profile: Profile
    )

    fun getProfileData(): Profile
}

class ProfileRepository @Inject constructor(
    private val prefsHelper: SharedPreferenceHelper
): ProfileRepositorySource {

    private val _profileData = MutableStateFlow<Profile>(getProfileData())
    val profileData: StateFlow<Profile> = _profileData

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