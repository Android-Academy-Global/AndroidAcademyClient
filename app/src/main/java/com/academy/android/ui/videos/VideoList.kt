package com.academy.android.ui.videos

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.academy.android.R
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun VideoList(videoList: List<VideosItemData>) {
    val paddingStandard = dimensionResource(id = R.dimen.spacing_16)
    LazyColumn(
        contentPadding = PaddingValues(all = paddingStandard),
        verticalArrangement = Arrangement.spacedBy(paddingStandard)
    ) {
        items(videoList) { video ->
            Card(
                shape = RoundedCornerShape(dimensionResource(id = R.dimen.news__card_corner_radius)),
                elevation = dimensionResource(id = R.dimen.news__card_elevation)
            ) {
                Column(
                    modifier = Modifier.padding(all = paddingStandard)
                ) {
                    GlideImage(
                        imageModel = video.picture,
                        contentScale = ContentScale.Crop,
                        contentDescription = stringResource(id = R.string.video_preview_img_desc),
                        placeHolder = painterResource(R.drawable.image_placeholder),
                        error = painterResource(R.drawable.image_error),
                        modifier = Modifier
                            .padding(bottom = dimensionResource(id = R.dimen.spacing_16))
                            .height(dimensionResource(id = R.dimen.videos_item_picture_height))
                    )
                    Text(text = video.name)
                    Text(text = video.date, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}