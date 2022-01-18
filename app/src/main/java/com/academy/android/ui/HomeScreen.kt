package com.academy.android.ui

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.academy.android.ui.news.NewsScreen
import com.academy.android.ui.profile.ProfileScreen
import com.academy.android.ui.videos.VideosScreen

@Composable
fun HomeScreen(navController: NavHostController) {
    AppBottomNavigation(navController = navController)
}

@Composable
private fun AppBottomNavigation(
    navController: NavHostController
) {
    var selectedItem: Screen by remember { mutableStateOf(Screen.News) }
    val items = listOf(
        Screen.News,
        Screen.Videos,
        Screen.Profile,
    )
    Scaffold(
        bottomBar = {
            BottomNavigation(
                backgroundColor = MaterialTheme.colors.background,
                contentColor = Green700
            ) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                items.forEach { screen ->
                    BottomNavigationItem(
                        icon = {
                            Icon(
                                painter = painterResource(id = screen.iconResId),
                                contentDescription = null
                            )
                        },
                        label = { Text(stringResource(screen.resourceId)) },
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                        onClick = { selectedItem = screen }
                    )
                }
            }
        }
    ) {
        // todo: do we need a better solution here?
        when (selectedItem) {
            is Screen.News -> NewsScreen()
            is Screen.Videos -> VideosScreen()
            is Screen.Profile -> ProfileScreen()
            else -> throw UnsupportedOperationException("This option ${selectedItem.javaClass} is not supported yet")
        }
    }
}