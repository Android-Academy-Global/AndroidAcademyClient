package com.academy.android.ui.profile

data class ProfileInfo(
    val profPic: String? = null,
    val profileItemList: List<ProfileItem> = emptyList(),
)