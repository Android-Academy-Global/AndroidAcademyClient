package com.academy.android.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.academy.android.R
import com.academy.android.data.repositories.ProfileRepository
import com.academy.android.model.Profile
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileRepo: ProfileRepository
) : ViewModel() {

    private val _profileData = MutableStateFlow(ProfileInfo())
    val profileData: StateFlow<ProfileInfo> = _profileData

    fun updateProfile(profile: Profile) {
        profileRepo.saveProfileData(profile)
    }

    fun changeProfileData(isEditable: Boolean) {
        viewModelScope.launch {
            _profileData.emit(
                ProfileInfo(
                    profPic = _profileData.value.profPic,
                    profileItemList = _profileData.value
                        .profileItemList
                        .map { item ->
                            ProfileItem(
                                value = item.value,
                                hintResId = item.hintResId,
                                isEditable = isEditable
                            )
                        }
                )
            )
        }
    }

    private fun buildProfileDataList(profileData: Profile) = listOf(
        ProfileItem(
            value = profileData.firstName,
            hintResId = R.string.edit_first_name
        ),
        ProfileItem(
            value = profileData.lastName,
            hintResId = R.string.edit_last_name
        ),
        ProfileItem(
            value = profileData.email,
            hintResId = R.string.edit_email
        ),
        ProfileItem(
            value = profileData.phoneNumber,
            hintResId = R.string.edit_phone
        ),
    )

    init {
        viewModelScope.launch {
            profileRepo.profileData.collect { profile ->
                _profileData.value = ProfileInfo(
                    profPic = profile.profPic,
                    profileItemList = buildProfileDataList(profileData = profile)
                )
            }
        }
    }
}