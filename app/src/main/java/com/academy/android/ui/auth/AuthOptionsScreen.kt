package com.academy.android.ui.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.academy.android.R

@Composable
fun AuthOptionsScreen(vm: AuthViewModel) {
    val paddingStandard = dimensionResource(id = R.dimen.spacing_16)
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.top_background),
            contentDescription = stringResource(id = R.string.top_bg_desc),
            modifier = Modifier
                .fillMaxWidth()
        )
        Text(
            text = stringResource(id = R.string.auth_option_subtitle),
            fontSize = dimensionResource(id = R.dimen.auth_options_subtitle_size).value.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(start = dimensionResource(id = R.dimen.spacing_56))
        )
        Text(
            text = stringResource(id = R.string.auth_option_title),
            fontSize = dimensionResource(id = R.dimen.auth_options_title_size).value.sp,
            modifier = Modifier.padding(start = paddingStandard, end = paddingStandard)
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = paddingStandard, end = paddingStandard),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = paddingStandard),
                onClick = { /*TODO*/ }
            ) {
                Text(text = stringResource(id = R.string.option_login))
            }
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { vm.onGuestModeClick() }
            ) {
                Text(text = stringResource(id = R.string.option_guest))
            }
        }
    }
}

//@Preview
//@Composable
//fun LoginOptionsScreenPreview() {
//    LoginOptionsScreen()
//}