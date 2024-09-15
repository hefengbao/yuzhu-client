package com.hefengbao.yuzhu.ui.screen.tweet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.ModeComment
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.hefengbao.yuzhu.common.ext.diffForHumans
import com.hefengbao.yuzhu.data.database.entity.PostEntity
import com.hefengbao.yuzhu.data.enums.PostType
import com.hefengbao.yuzhu.data.model.User
import com.hefengbao.yuzhu.ui.component.Avatar

@Composable
fun TweetIndexRoute(
    viewModel: TweetIndexViewModel = hiltViewModel(),
    onCreateClick: () -> Unit,
    onItemClick: (id: Int) -> Unit,
) {
    val pagingItems = viewModel.tweets.collectAsLazyPagingItems()

    TweetIndexScreen(
        pagingItems = pagingItems,
        onCreateClick = onCreateClick,
        onItemClick = onItemClick
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TweetIndexScreen(
    modifier: Modifier = Modifier,
    pagingItems: LazyPagingItems<PostEntity>,
    onCreateClick: () -> Unit,
    onItemClick: (id: Int) -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "微博")
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onCreateClick) {
                Icon(imageVector = Icons.Outlined.Edit, contentDescription = null)
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = modifier.padding(paddingValues)
        ) {
            when (pagingItems.loadState.refresh) {
                is LoadState.Error -> {

                }

                LoadState.Loading -> {
                    item {
                        LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
                    }
                }

                is LoadState.NotLoading -> {}
            }

            items(
                count = pagingItems.itemCount,
                key = pagingItems.itemKey { it.id }
            ) { index ->
                if (pagingItems.itemCount > 0) {
                    pagingItems[index]?.let { entity ->
                        TweetCard(tweet = entity, onItemClick = onItemClick)
                    }
                } else {
                    Text(
                        modifier = Modifier.fillMaxSize(),
                        text = "空空如也",
                        textAlign = TextAlign.Center
                    )
                }
            }

            when (pagingItems.loadState.append) {
                is LoadState.Error -> {}
                LoadState.Loading -> {
                    item {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                        ) {
                            CircularProgressIndicator(
                                modifier = Modifier.align(Alignment.CenterVertically)
                            )
                        }
                    }
                }

                is LoadState.NotLoading -> {}
            }
        }
    }
}


@Composable
private fun TweetCard(
    modifier: Modifier = Modifier,
    tweet: PostEntity,
    onItemClick: (id: Int) -> Unit,
) {
    val context = LocalContext.current

    Card(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth(),
        onClick = { onItemClick(tweet.id) }
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Avatar(userId = tweet.author.id, userAvatar = tweet.author.avatar, onClick = {})
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
            Text(
                text = tweet.body.replace("<[^<]+?>", ""),
                maxLines = 5
            )
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { onItemClick(tweet.id) }) {
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

@Preview
@Composable
private fun TweetCardPreview(modifier: Modifier = Modifier) {
    TweetCard(
        tweet = PostEntity(
            id = 1,
            title = null,
            slug = "",
            url = "",
            excerpt = "",
            body = """  
                # Sample  
                * Markdown  
                * [Link](https://example.com)  
                ![Image](https://example.com/img.png)  
                <a href="https://www.google.com/">Google</a>  
            """,
            bodyHtml = "",
            type = PostType.Tweet.type,
            commentable = "open",
            status = "published",
            commentCount = 0,
            publishedAt = null,
            pinnedAt = null,
            createdAt = "2024-07-01T01:23:26+00:00",
            updatedAt = "2024-07-01T01:23:26+00:00",
            author = User(
                id = 1,
                name = "bao",
                avatar = "",
                bio = "",
                role = "administrator"
            ),
            categories = emptyList(),
            tags = emptyList(),
            canEdit = true,
            canDelete = true
        ),
        onItemClick = {},
    )
}
