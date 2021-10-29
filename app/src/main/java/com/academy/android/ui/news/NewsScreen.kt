package com.academy.android.ui.news

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.academy.android.R

@Composable
fun NewsScreen(vm: NewsViewModel) {
    val newsList: List<NewsItemData> by vm.newsList.collectAsState(initial = listOf())
    NewsFeedView(newsList = newsList)
}

@Composable
private fun NewsFeedView(newsList: List<NewsItemData>) {
    var tabIndex by remember { mutableStateOf(0) }
    val tabNames: List<String> = listOf(
        stringResource(id = R.string.news__tab_title_featured),
        stringResource(id = R.string.news__tab_title_passed)
    )

    Column {
        TabRow(
            selectedTabIndex = tabIndex,
            backgroundColor = Color(R.color.dark_green),
            contentColor = Color(R.color.white)
        ) {
            tabNames.withIndex()
                .forEach { tab ->
                    Tab(
                        selected = (tabIndex == tab.index),
                        onClick = { tabIndex = tab.index },
                        text = { Text(text = tab.value) })
                }
        }
        NewsList(newsList = newsList)
    }
}