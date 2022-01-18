package com.academy.android.ui.auth

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults.MinHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.sp
import com.academy.android.R
import com.academy.android.domain.models.AuthState
import com.academy.android.ui.Red800
import com.academy.android.ui.TextColorSecondary

@Composable
fun AuthOptionsScreen(vm: AuthViewModel) {
    val authState by vm.authState.collectAsState()

    val paddingStandard = dimensionResource(id = R.dimen.spacing_16)
    val paddingSmall = dimensionResource(id = R.dimen.spacing_8)
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
//        Image(
//            painter = painterResource(id = R.drawable.top_background),
//            contentDescription = stringResource(id = R.string.top_bg_desc),
//            modifier = Modifier
//                .fillMaxWidth(),
//            alignment = Alignment.TopCenter
//        )
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

        Column(modifier = Modifier.padding(all = paddingStandard)) {
            val username = remember { mutableStateOf("") }
            val pwd = remember { mutableStateOf("") }
            val isMentor = remember { mutableStateOf(false) }

            // fixme: windowSoftInputMode=adjustResize not working
            val focusManger = LocalFocusManager.current
            // todo: add register option
            // todo: add google sign up
            val isError = (authState == AuthState.UNAUTHORIZED)
            OutlinedTextField(
                value = username.value,
                onValueChange = { newValue -> username.value = newValue },
                label = { Text(text = stringResource(id = R.string.username_hint)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = paddingStandard, bottom = paddingSmall),
                singleLine = true,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                keyboardActions = KeyboardActions(
                    onNext = { focusManger.moveFocus(FocusDirection.Down) }
                ),
                isError = isError
            )
            val passwordVisibility = remember { mutableStateOf(false) }
            OutlinedTextField(
                value = pwd.value,
                onValueChange = { newValue -> pwd.value = newValue },
                label = { Text(text = stringResource(id = R.string.pwd_hint)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = paddingSmall),
                singleLine = true,
                keyboardActions = KeyboardActions(
                    onDone = { focusManger.clearFocus() }
                ),
                visualTransformation = if (passwordVisibility.value) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    IconButton(onClick = {
                        passwordVisibility.value = !passwordVisibility.value
                    }) {
                        Icon(
                            painterResource(
                                id = if (passwordVisibility.value) {
                                    R.drawable.ic_password_visible
                                } else {
                                    R.drawable.ic_password_hidden
                                }
                            ),
                            contentDescription = null,
                            modifier = Modifier.size(dimensionResource(id = R.dimen.eye_icon_size))
                        )
                    }
                },
                isError = isError
            )
            if (isError) {
                Text(
                    text = stringResource(id = R.string.error_wrong_credentials),
                    color = Red800,
                    modifier = Modifier.padding(start = paddingSmall, bottom = paddingSmall)
                )
            }
            Row(
                modifier = Modifier
                    .padding(bottom = paddingSmall),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Switch(
                    checked = isMentor.value,
                    onCheckedChange = { newValue -> isMentor.value = newValue },
                    modifier = Modifier.padding(start = paddingSmall)
                )
                Text(
                    text = stringResource(id = R.string.auth_mentor),
                    modifier = Modifier.padding(start = paddingSmall, end = paddingSmall)
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = stringResource(id = R.string.forgot_pwd),
                    color = TextColorSecondary,
                    fontStyle = FontStyle.Italic,
                    modifier = Modifier
                        .padding(end = paddingSmall)
                )
            }
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(MinHeight)
                    .padding(bottom = paddingSmall),
                onClick = { vm.onLogInClick(username = username.value, password = pwd.value) }
            ) {
                Text(text = stringResource(id = R.string.option_login))
            }
            Text(
                text = stringResource(id = R.string.auth_or),
                color = TextColorSecondary,
                modifier = Modifier
                    .padding(bottom = paddingSmall)
                    .align(Alignment.CenterHorizontally)
            )
            OutlinedButton(
                modifier = Modifier
                    .height(MinHeight)
                    .fillMaxWidth()
                    .padding(bottom = paddingSmall),
                onClick = { vm.onGuestModeClick() }
            ) {
                Text(text = stringResource(id = R.string.option_register))
            }
            OutlinedButton(
                modifier = Modifier
                    .height(MinHeight)
                    .fillMaxWidth(),
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