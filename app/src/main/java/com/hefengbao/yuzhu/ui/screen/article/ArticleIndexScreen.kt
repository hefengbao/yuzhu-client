package com.hefengbao.yuzhu.ui.screen.article

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.hefengbao.yuzhu.common.ext.diffForHumans
import com.hefengbao.yuzhu.data.database.entity.PostEntity
import com.hefengbao.yuzhu.ui.component.Avatar

@Composable
fun ArticleIndexRoute(
    viewModel: ArticleIndexViewModel = hiltViewModel(),
    onItemClick: (Int) -> Unit
) {
    val pagingItems = viewModel.articles.collectAsLazyPagingItems()

    ArticleIndexScreen(
        onItemClick = onItemClick,
        pagingItems = pagingItems
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ArticleIndexScreen(
    modifier: Modifier = Modifier,
    onItemClick: (Int) -> Unit,
    pagingItems: LazyPagingItems<PostEntity>
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "博客")
                }
            )
        }
    ) { paddingValues ->

        val context = LocalContext.current

        LazyColumn(
            modifier = modifier.padding(paddingValues)
        ) {
            when (val state = pagingItems.loadState.refresh) {
                is LoadState.Error -> {
                    item {
                        Column(
                            modifier = Modifier.fillMaxSize().padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            Text(text = "${state.error.message}")
                            Button(
                                onClick = { pagingItems.refresh() },
                                modifier = Modifier.align(Alignment.CenterHorizontally)
                            ) {
                                Text(text = "重试")
                            }
                        }
                    }
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
                pagingItems[index]?.let { entity ->
                    Card(
                        modifier = modifier.padding(16.dp),
                        onClick = { onItemClick(entity.id) }
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text(
                                text = "${entity.title}",
                                style = MaterialTheme.typography.titleMedium
                            )
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Avatar(
                                    userId = entity.author.id,
                                    userAvatar = entity.author.avatar,
                                    onClick = {},
                                    size = 12.dp
                                )
                                Text(
                                    text = entity.author.name ?: "",
                                    style = MaterialTheme.typography.labelMedium.copy(
                                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = .5f)
                                    )
                                )
                                Text(
                                    text = entity.publishedAt.diffForHumans(context),
                                    style = MaterialTheme.typography.labelMedium.copy(
                                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = .5f)
                                    )
                                )
                            }
                            Text(text = entity.excerpt ?: "")
                        }
                    }
                }
            }

            when (val state = pagingItems.loadState.append) {
                is LoadState.Error -> {
                    item {
                        Column(
                            modifier = Modifier.fillMaxSize().padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            Text(text = "${state.error.message}")
                            Button(
                                onClick = { pagingItems.retry() },
                                modifier = Modifier.align(Alignment.CenterHorizontally)
                            ) {
                                Text(text = "重试")
                            }
                        }
                    }
                }
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