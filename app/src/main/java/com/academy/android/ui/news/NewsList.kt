package com.academy.android.ui.news

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.academy.android.R

@Composable
fun NewsList(newsList: List<NewsItemData>) {
    val standardMargin = dimensionResource(id = R.dimen.spacing_16)
    LazyColumn(
        modifier = Modifier.padding(horizontal = standardMargin),
        contentPadding = PaddingValues(vertical = standardMargin),
        verticalArrangement = Arrangement.spacedBy(standardMargin)
    ) {
        items(newsList) { newsItem ->
            NewsItem(
                shortText = newsItem.text,
                likesCount = newsItem.likesCount,
                commentsCount = newsItem.commentsCount,
                thumbnailUrl = newsItem.picture
            )
        }
    }
}