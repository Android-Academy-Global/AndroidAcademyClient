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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.academy.android.R
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun ProfileScreen(vm: ProfileViewModel) {
    val profileData: ProfileInfo by vm.profileData.collectAsState(initial = ProfileInfo())

    val marginStandard = dimensionResource(id = R.dimen.spacing_16)
    val marginDouble = dimensionResource(id = R.dimen.spacing_32)
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        TopControls(marginStandard = marginStandard, marginDouble = marginDouble, vm = vm)
        Avatar(profPic = profileData.profPic)
        ProfileItemList(marginStandard = marginStandard, marginDouble = marginDouble, profileItemList = profileData.profileItemList)
    }
}

@Composable
private fun TopControls(
    marginStandard: Dp,
    marginDouble: Dp,
    vm: ProfileViewModel
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
        Icon(
            painter = painterResource(id = R.drawable.ic_logout),
            contentDescription = stringResource(id = R.string.logout_desc)
        )
        IconButton(onClick = { vm.changeProfileData(isEditable = true) }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_edit),
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
    profileItemList: List<ProfileItem>
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(marginStandard),
        modifier = Modifier.padding(top = marginDouble)
    ) {
        items(profileItemList) { profileItem ->
            ProfileDataItem(
                value = profileItem.value,
                hintResId = profileItem.hintResId,
                isEditable = profileItem.isEditable
            )
        }
    }
}

@Composable
fun ProfileDataItem(value: String?, hintResId: Int, isEditable: Boolean) {
    var text by remember { mutableStateOf(value ?: "") }

    OutlinedTextField(
        value = text,
        onValueChange = { newText -> text = newText },
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