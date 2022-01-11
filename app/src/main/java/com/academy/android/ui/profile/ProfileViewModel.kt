package com.academy.android.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.academy.android.R
import com.academy.android.domain.OperationResult
import com.academy.android.domain.models.UserProfile
import com.academy.android.domain.models.UserTitle
import com.academy.android.domain.repositories.AuthRepository
import com.academy.android.domain.repositories.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProfileViewModel @Inject constructor(
    authRepository: AuthRepository,
    private val profileRepo: ProfileRepository
) : ViewModel() {

    private val _profileData = MutableStateFlow(ProfileInfo())
    val profileData: StateFlow<ProfileInfo> = _profileData

    private val _isInEditMode = MutableStateFlow(false)
    val isInEditMode = _isInEditMode

    private val _profileDataDraft = MutableStateFlow(UserProfile.Builder())

    init {
        viewModelScope.launch {
            authRepository.userProfile.collect { profileResult: OperationResult<UserProfile, Throwable?> ->
                when (profileResult) {
                    is OperationResult.Success -> {
                        _profileData.value = ProfileInfo(
                            profPic = profileResult.data.profPic,
                            profileItemList = buildProfileDataList(profileData = profileResult.data)
                        )
                    }
                }
            }
        }
    }

    fun updateProfile() {
        viewModelScope.launch {
            profileRepo.saveProfileData(_profileDataDraft.value.build())
        }
    }

    fun switchEditingMode(isEditable: Boolean) {
        _isInEditMode.tryEmit(isEditable)
    }

    fun discardChanges() {
        _profileData.tryEmit(
            ProfileInfo(
                profPic = _profileData.value.profPic,
                profileItemList = _profileData.value.profileItemList,
                isChangesDiscarded = true
            )
        )
        switchEditingMode(isEditable = false)
        _profileDataDraft.tryEmit(UserProfile.Builder())
    }

    private fun buildProfileDataList(profileData: UserProfile) = listOf(
        ProfileItem(
            value = profileData.firstName,
            hintResId = R.string.edit_first_name,
            onValueChanged = { newValue ->
                _profileDataDraft.tryEmit(
                    _profileDataDraft.value.withFirstName(newValue) // fixme think of a better/threadSafe way
                )
            }
        ),
        ProfileItem(
            value = profileData.lastName,
            hintResId = R.string.edit_last_name,
            onValueChanged = { newValue ->
                _profileDataDraft.tryEmit(
                    _profileDataDraft.value.withLastName(newValue)
                )
            }
        ),
        ProfileItem(
            value = profileData.email,
            hintResId = R.string.edit_email,
            onValueChanged = { newValue ->
                _profileDataDraft.tryEmit(
                    _profileDataDraft.value.withEmail(newValue)
                )
            }
        ),
        ProfileItem(
            value = profileData.phoneNumber,
            hintResId = R.string.edit_phone,
            onValueChanged = { newValue ->
                _profileDataDraft.tryEmit(
                    _profileDataDraft.value.withPhoneNumber(newValue)
                )
            }
        ),
        ProfileItem(
            value = profileData.title?.name,
            hintResId = R.string.edit_status,
            onValueChanged = { newValue ->
                _profileDataDraft.tryEmit(
                    _profileDataDraft.value.withTitle(UserTitle.valueOf(newValue))
                )
            }
        ),
    )
}