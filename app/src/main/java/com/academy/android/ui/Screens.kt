package com.academy.android.ui

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.academy.android.R

sealed class Screen(val route: String, @StringRes val resourceId: Int = 0, @DrawableRes val iconResId: Int = 0) {
    object Splash : Screen(route = "splash")
    object Start : Screen(route = "start")
    object News : Screen(route = "news", resourceId = R.string.title_news, iconResId = R.drawable.ic_news)
    object Videos : Screen(route = "videos", resourceId = R.string.title_videos, iconResId = R.drawable.ic_videos)
    object Profile : Screen(route = "profile", resourceId = R.string.title_profile, iconResId = R.drawable.ic_profile)
    object HomeGraph : Screen(route = "home_graph")
}