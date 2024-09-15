package com.hefengbao.yuzhu.ui.screen.article

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.hefengbao.yuzhu.R
import com.hefengbao.yuzhu.common.ext.diffForHumans
import com.hefengbao.yuzhu.common.ext.showToast
import com.hefengbao.yuzhu.data.database.entity.CommentEntity
import com.hefengbao.yuzhu.data.database.entity.PostEntity
import com.hefengbao.yuzhu.ui.component.Avatar
import com.hefengbao.yuzhu.ui.component.SimpleScaffold
import com.hefengbao.yuzhu.ui.component.webview.RYWebView
import dev.jeziellago.compose.markdowntext.MarkdownText

@Composable
fun ArticleShowRoute(
    viewModel: ArticleShowViewModel = hiltViewModel(),
    onBackClick: () -> Unit
) {
    val article by viewModel.article.collectAsState(initial = null)
    val pagingItems = viewModel.comments.collectAsLazyPagingItems()

    val context = LocalContext.current

    article?.let { entity ->
        ArticleShowScreen(
            onBackClick = onBackClick,
            article = entity,
            pagingItems = pagingItems,
            onDownloadImage = {
                viewModel.downloadImage(
                    it,
                    onSuccess = { context.showToast(context.getString(R.string.image_saved)) },
                    onFailure = {
                        // FIXME: crash the app for error report
                            th ->
                        throw th
                    }
                )
            }
        )
    }
}

@Composable
private fun ArticleShowScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    article: PostEntity,
    pagingItems: LazyPagingItems<CommentEntity>,
    onDownloadImage: (String) -> Unit,
) {
    val context = LocalContext.current

    var currentImageData by remember { mutableStateOf(ImageData()) }
    var showFullScreenImageViewer by remember { mutableStateOf(false) }

    SimpleScaffold(onBackClick = onBackClick, title = "博客") {
        LazyColumn(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            item {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    Text(text = "${article.title}", style = MaterialTheme.typography.titleMedium)
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Avatar(
                            userId = article.author.id,
                            userAvatar = article.author.avatar,
                            onClick = {},
                            size = 12.dp
                        )
                        Text(
                            text = article.author.name ?: "",
                            style = MaterialTheme.typography.labelMedium.copy(
                                color = MaterialTheme.colorScheme.onBackground.copy(alpha = .5f)
                            )
                        )
                        Text(
                            text = article.publishedAt.diffForHumans(context),
                            style = MaterialTheme.typography.labelMedium.copy(
                                color = MaterialTheme.colorScheme.onBackground.copy(alpha = .5f)
                            )
                        )
                    }
                    SelectionContainer {
                        RYWebView(
                            content = article.bodyHtml,
                            onImageClick = { imgUrl: String, altText: String ->
                                currentImageData = ImageData(imgUrl, altText)
                                showFullScreenImageViewer = true
                            }
                        )
                    }
                }
            }
            item {
                Text(
                    text = "评论",
                    style = MaterialTheme.typography.titleSmall,
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
                                .padding(vertical = 8.dp)
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
                item {
                    Text(
                        text = "没有评论！",
                    )
                }
            }
        }
    }

    if (showFullScreenImageViewer) {

        ReaderImageViewer(
            imageData = currentImageData,
            onDownloadImage = onDownloadImage,
            onDismissRequest = { showFullScreenImageViewer = false }
        )
    }
}