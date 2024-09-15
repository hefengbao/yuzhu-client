package com.hefengbao.yuzhu.ui.screen.tweet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ModeComment
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.hefengbao.yuzhu.R
import com.hefengbao.yuzhu.common.ext.diffForHumans
import com.hefengbao.yuzhu.data.database.entity.CommentEntity
import com.hefengbao.yuzhu.data.database.entity.PostEntity
import com.hefengbao.yuzhu.ui.component.Avatar
import com.hefengbao.yuzhu.ui.component.SimpleScaffold
import com.hefengbao.yuzhu.ui.component.webview.RYWebView
import dev.jeziellago.compose.markdowntext.MarkdownText

@Composable
fun TweetShowRoute(
    viewModel: TweetShowViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
) {
    val tweet by viewModel.tweet.collectAsState(initial = null)
    val comments = viewModel.comments.collectAsLazyPagingItems()

    tweet?.let { entity ->
        TweetShowScreen(onBackClick = onBackClick, tweet = entity, pagingItems = comments)
    }
}

@Composable
private fun TweetShowScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    tweet: PostEntity,
    pagingItems: LazyPagingItems<CommentEntity>
) {
    SimpleScaffold(
        onBackClick = onBackClick,
        title = stringResource(id = R.string.tweet)
    ) {
        val context = LocalContext.current

        LazyColumn(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            item {
                Card(
                    modifier = modifier.fillMaxWidth(),
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            Avatar(
                                userId = tweet.author.id,
                                userAvatar = tweet.author.avatar,
                                onClick = {})
                            Column(
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Text(text = tweet.author.name ?: "")
                                Text(
                                    text = tweet.createdAt.diffForHumans(context),
                                    style = MaterialTheme.typography.labelMedium.copy(
                                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = .5f)
                                    )
                                )
                            }
                        }
                        SelectionContainer {
                            RYWebView(
                                content = tweet.bodyHtml,
                                onImageClick = { imgUrl: String, altText: String -> }
                            )
                        }
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            IconButton(onClick = { }) {
                                Icon(
                                    modifier = Modifier.size(18.dp),
                                    imageVector = Icons.Outlined.ModeComment,
                                    contentDescription = "评论"
                                )
                            }
                            if (tweet.commentCount > 0) {
                                Text(
                                    text = "${tweet.commentCount}",
                                    style = MaterialTheme.typography.labelMedium.copy(
                                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = .5f)
                                    )
                                )
                            }
                        }
                    }
                }
            }
            item {
                Text(
                    text = "评论",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = modifier.padding(vertical = 16.dp)
                )
            }

            if (pagingItems.itemCount > 0){
                items(
                    count = pagingItems.itemCount,
                ) { index ->
                    val entity = pagingItems[index]
                    entity?.let {
                        Row(
                            modifier = modifier
                                .padding(16.dp)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            if (entity.author != null) {
                                Avatar(
                                    userId = entity.author.id,
                                    userAvatar = entity.author.avatar,
                                    onClick = {})
                            } else {
                                Avatar(userId = null, userAvatar = null, onClick = {})
                            }
                            Column(
                                modifier = Modifier.fillMaxWidth(),
                                verticalArrangement = Arrangement.spacedBy(16.dp)
                            ) {
                                Column(
                                    verticalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    if (entity.author != null) {
                                        Text(
                                            text = entity.author.name ?: "",
                                            fontWeight = FontWeight.Bold
                                        )
                                    } else {
                                        Text(
                                            text = entity.guestName ?: "",
                                            fontWeight = FontWeight.Bold
                                        )
                                    }
                                    MarkdownText(markdown = entity.body)
                                    Text(
                                        text = entity.createdAt.diffForHumans(context),
                                        style = MaterialTheme.typography.bodySmall.copy(
                                            brush = null,
                                            alpha = .5f
                                        )
                                    )
                                }

                            }
                        }
                    }
                }
            }else{
                item{
                    Text(
                        text = "没有评论！",
                        modifier = modifier.fillMaxSize()
                    )
                }
            }
        }
    }
}
