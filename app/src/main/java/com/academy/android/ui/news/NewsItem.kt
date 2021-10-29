package com.academy.android.ui.news

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.academy.android.R
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun NewsItem(shortText: String, likesCount: Int, commentsCount: Int, thumbnailUrl: String) {
    CardLayout(shortText = shortText, likesCount = likesCount, commentsCount = commentsCount, thumbnailUrl = thumbnailUrl)
}

//@Preview
//@Composable
//fun NewsCardPreview() {
//    CardLayout(
//        shortText = """В понедельник было не просто! Артур поведал много всего про реактивные подходы и саму библиотеку RxJava. Хоть домашки к этой лекции не будет, но скучать не придется. Ведь есть RxRiddles.""",
//        thumbnailUrl = painterResource(R.drawable.lecture_image_sample)
//    )
//}

@Composable
private fun CardLayout(
    shortText: String,
    likesCount: Int,
    commentsCount: Int,
    thumbnailUrl: String
) {
    Card(
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.news__card_corner_radius)),
        elevation = dimensionResource(id = R.dimen.news__card_elevation)
    ) {
        Column(
            modifier = Modifier.padding(all = dimensionResource(id = R.dimen.spacing_16))
        ) {
            Text(text = shortText)
            GlideImage(
                imageModel = thumbnailUrl,
                contentScale = ContentScale.Crop,
                contentDescription = stringResource(id = R.string.lecture_image_desc),
                placeHolder = painterResource(R.drawable.image_placeholder),
                error = painterResource(R.drawable.image_error),
                modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.spacing_16))
                    .height(dimensionResource(id = R.dimen.news__item_picture_height))
            )
            Row {
                IconCounterBlock(count = likesCount, iconResId = R.drawable.ic_like, imageDescResId = R.string.like_image_desc)
                IconCounterBlock(count = commentsCount, iconResId = R.drawable.ic_discuss, imageDescResId = R.string.comment_image_desc)
                Spacer(
                    modifier = Modifier
                        .width(0.dp)
                        .weight(0.7f)
                )
                Image(
                    painter = painterResource(id = R.drawable.ic_share),
                    contentDescription = stringResource(id = R.string.share_image_desc)
                )
            }
        }
    }
}

@Composable
private fun IconCounterBlock(count: Int, @DrawableRes iconResId: Int, imageDescResId: Int) {
    Image(
        painter = painterResource(id = iconResId),
        contentDescription = stringResource(id = imageDescResId),
        modifier = Modifier.padding(end = dimensionResource(id = R.dimen.spacing_8))
    )
    Text(
        text = count.toString(),
        modifier = Modifier.padding(end = dimensionResource(id = R.dimen.spacing_16))
    )
}