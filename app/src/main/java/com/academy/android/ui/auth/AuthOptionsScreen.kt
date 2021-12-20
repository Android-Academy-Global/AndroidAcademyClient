package com.academy.android.ui.auth

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
import androidx.compose.ui.res.stringResource
import com.academy.android.R

@Composable
fun AuthOptionsScreen(vm: AuthViewModel) {
    val paddingStandard = dimensionResource(id = R.dimen.spacing_16)
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

//@Preview
//@Composable
//fun LoginOptionsScreenPreview() {
//    LoginOptionsScreen()
//}