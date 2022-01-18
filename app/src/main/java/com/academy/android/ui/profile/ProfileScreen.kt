package com.academy.android.ui.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.academy.android.R
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun ProfileScreen(vm: ProfileViewModel = hiltViewModel()) {
    val profileData: ProfileInfo by vm.profileData.collectAsState(initial = ProfileInfo())
    val isInEditMode: Boolean by vm.isInEditMode.collectAsState(initial = false)

    val marginStandard = dimensionResource(id = R.dimen.spacing_16)
    val marginDouble = dimensionResource(id = R.dimen.spacing_32)
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        TopControls(
            marginStandard = marginStandard,
            marginDouble = marginDouble,
            vm = vm,
            isInEditMode = isInEditMode
        )
        Avatar(profPic = profileData.profPic)
        ProfileItemList(
            marginStandard = marginStandard,
            marginDouble = marginDouble,
            profileItemList = profileData.profileItemList,
            isInEditMode = isInEditMode,
            isChangesDiscarded = profileData.isChangesDiscarded
        )
    }
}

@Composable
private fun TopControls(
    marginStandard: Dp,
    marginDouble: Dp,
    vm: ProfileViewModel,
    isInEditMode: Boolean
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = marginStandard,
                start = marginStandard,
                end = marginStandard,
                bottom = marginDouble
            ),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        val focusManger = LocalFocusManager.current
        IconButton(onClick = {
            if (isInEditMode) {
                vm.discardChanges()
                focusManger.clearFocus()
            }
        }) {
            Icon(
                painter = painterResource(
                    id = if (isInEditMode) {
                        R.drawable.ic_clear
                    } else {
                        R.drawable.ic_logout
                    }
                ),
                contentDescription = stringResource(id = R.string.logout_desc)
            )
        }
        IconButton(onClick = {
            if (isInEditMode) {
                vm.updateProfile()
            }
            vm.switchEditingMode(isEditable = !isInEditMode)
            focusManger.clearFocus()
        }) {
            Icon(
                painter = painterResource(
                    id = if (isInEditMode) {
                        R.drawable.ic_done
                    } else {
                        R.drawable.ic_edit
                    }
                ),
                contentDescription = stringResource(id = R.string.edit_profile_desc),
            )
        }
    }
}

@Composable
private fun Avatar(profPic: String?) {
    val circleImage = Modifier
        .size(80.dp)
        .clip(CircleShape)
    if (profPic != null) {
        GlideImage(
            imageModel = profPic,
            contentDescription = stringResource(id = R.string.user_profile_pic_desc),
            modifier = circleImage
        )
    } else {
        Image(
            painter = painterResource(id = R.drawable.avatar_placeholder),
            contentDescription = stringResource(id = R.string.user_profile_pic_desc),
            modifier = circleImage
        )
    }
}

@Composable
private fun ProfileItemList(
    marginStandard: Dp,
    marginDouble: Dp,
    profileItemList: List<ProfileItem>,
    isInEditMode: Boolean,
    isChangesDiscarded: Boolean
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(marginStandard),
        modifier = Modifier.padding(top = marginDouble)
    ) {
        items(profileItemList) { profileItem ->
            ProfileDataItem(
                value = profileItem.value,
                hintResId = profileItem.hintResId,
                isEditable = isInEditMode,
                onValueChanged = profileItem.onValueChanged,
                isChangesDiscarded = isChangesDiscarded
            )
        }
    }
}

@Composable
fun ProfileDataItem(
    value: String?,
    hintResId: Int,
    isEditable: Boolean,
    onValueChanged: (String) -> Unit,
    isChangesDiscarded: Boolean
) {
    val text = remember { mutableStateOf(value ?: "") }

    if (isChangesDiscarded) {
        text.value = value ?: ""
    }

    OutlinedTextField(
        value = text.value,
        onValueChange = { newValue ->
            text.value = newValue
            onValueChanged(newValue)
        },
        label = { Text(text = stringResource(id = hintResId)) },
        readOnly = !isEditable,
        modifier = Modifier
            .padding(
                start = dimensionResource(id = R.dimen.spacing_16),
                end = dimensionResource(id = R.dimen.spacing_16)
            )
            .fillMaxWidth()
    )
}