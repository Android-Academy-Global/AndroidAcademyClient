package com.academy.android.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.academy.android.R
import kotlinx.coroutines.delay

private const val THREE_SECONDS_DELAY: Long = 3000

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SplashScreen(navController: NavHostController) {
    val state = remember {
        MutableTransitionState(false).apply {
            // Start the animation immediately.
            targetState = true
        }
    }
    LaunchedEffect(key1 = true) {
        delay(THREE_SECONDS_DELAY)
        navController.navigate(Screen.Start.route) {
            popUpTo(Screen.Splash.route) {
                inclusive = true
            }
        }
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AnimatedVisibility(visibleState = state) {
            Image(
                painter = painterResource(id = R.drawable.academy_droid),
                contentDescription = stringResource(id = R.string.splash_icon_desc)
            )
        }
    }
}