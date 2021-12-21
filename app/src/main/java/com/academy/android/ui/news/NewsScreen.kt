package com.academy.android.ui.news

import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import com.academy.android.R
import com.academy.android.ui.Green700

private const val FEATURED_TAB_INDEX = 0
private const val PASSED_TAB_INDEX = 1

@Composable
fun NewsScreen(vm: NewsViewModel) {
    val newsList: List<NewsItemData> by vm.newsList.collectAsState(initial = listOf())
    NewsFeedView(newsList = newsList, vm = vm)
}

@Composable
private fun NewsFeedView(newsList: List<NewsItemData>, vm: NewsViewModel) {
    var tabIndex by remember { mutableStateOf(0) }
    val tabNames: Map<Int, String> = mapOf(
        FEATURED_TAB_INDEX to stringResource(id = R.string.news__tab_title_featured),
        PASSED_TAB_INDEX to stringResource(id = R.string.news__tab_title_passed)
    )

    Column {
        TabRow(
            selectedTabIndex = tabIndex,
            backgroundColor = MaterialTheme.colors.background,
            contentColor = Green700
        ) {
            tabNames.forEach { tab ->
                    Tab(
                        selected = (tabIndex == tab.key),
                        onClick = {
                            tabIndex = tab.key
                            when (tabIndex) {
                                FEATURED_TAB_INDEX -> vm.applyFilterNew()
                                PASSED_TAB_INDEX -> vm.applyFilterOld()
                            }
                        },
                        text = { Text(text = tab.value) }
                    )
                }
        }
        NewsList(newsList = newsList)
    }
}