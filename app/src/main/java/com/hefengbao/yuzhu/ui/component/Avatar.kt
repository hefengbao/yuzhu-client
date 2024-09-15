package com.hefengbao.yuzhu.ui.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Avatar(
    modifier: Modifier = Modifier,
    userId: Int?,
    userAvatar: String?,
    onClick: (Int) -> Unit,
    size: Dp = 48.dp
) {
    AsyncImage(
        model = userAvatar,
        modifier = modifier
            .size(size)
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.primary.copy(alpha = .5f))
            .combinedClickable(
                onClick = {
                    userId?.let(onClick)
                },
                onLongClick = {

                }
            ),
        contentScale = ContentScale.Inside,
        contentDescription = "Avatar",
    )
}

@Preview
@Composable
private fun AvatarPreview(
    modifier: Modifier = Modifier
) {
    Avatar(
        userId = 1,
        userAvatar = "https://www.8ug.icu/storage/upload/avatars/kGdajXB9pRvJeANsofc4I7xvx7cOKSnNmFLvpv1L.png",
        onClick = {}
    )
}