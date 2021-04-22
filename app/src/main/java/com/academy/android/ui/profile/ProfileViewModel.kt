package com.academy.android.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
): ViewModel() {

    private val _profileData = MutableStateFlow(Profile())
    val profileData: StateFlow<Profile> = _profileData

    fun updateProfile(profile: Profile) {
        profileRepo.saveProfileData(profile)
    }

    init {
        viewModelScope.launch {
            profileRepo.profileData.collect {
                _profileData.value = it
            }
        }
    }
}