package com.academy.android.data.repositories

import com.academy.android.model.Profile
import com.academy.android.util.SharedPreferenceHelper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
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

    val profileData: Flow<Profile> = flow {
        val profile = getProfileData()
        emit(profile)
    }

    override fun saveProfileData(profile: Profile) {
        prefsHelper.saveProfData(profile)
    }

    override fun getProfileData(): Profile {
        return prefsHelper.getProfData()
    }


}