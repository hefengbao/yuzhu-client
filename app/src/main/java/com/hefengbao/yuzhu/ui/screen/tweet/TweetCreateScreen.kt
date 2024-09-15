package com.hefengbao.yuzhu.ui.screen.tweet

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Tag
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import com.hefengbao.yuzhu.common.network.Result
import com.hefengbao.yuzhu.data.enums.Commentable
import com.hefengbao.yuzhu.data.model.Post
import com.hefengbao.yuzhu.data.model.Tag
import com.hefengbao.yuzhu.ui.component.SimpleScaffold

@Composable
fun TweetCreateRoute(
    viewModel: TweetCreateViewModel = hiltViewModel(),
    backStackEntry: NavBackStackEntry,
    onBackClick: () -> Unit,
    onAddTagClick: () -> Unit,
) {
    val saveStateHandle = backStackEntry.savedStateHandle
    if (saveStateHandle.contains("tag")) {
        saveStateHandle.get<Tag?>("tag")?.let {
            viewModel.addTag(it)
        }
        saveStateHandle.remove<Tag>("tag")
    }

    val tweetResult by viewModel.tweetResult.collectAsState(initial = null)

    TweetCreateScreen(
        onBackClick = onBackClick,
        onAddTagClick = onAddTagClick,
        onRemoveTagClick = { viewModel.deleteTag(it) },
        onSaveClick = { body: String, commentable: Commentable, tags: List<Tag> ->
            viewModel.save(
                body,
                commentable,
                tags
            )
        },
        tags = viewModel.tags,
        tweetResult = tweetResult
    )
}

@Composable
fun TweetCreateScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onAddTagClick: () -> Unit,
    onRemoveTagClick: (Int) -> Unit,
    onSaveClick: (body: String, commentable: Commentable, tags: List<Tag>) -> Unit,
    tags: List<Tag>,
    tweetResult: Result<Post>?
) {
    val context = LocalContext.current

    var body by rememberSaveable {
        mutableStateOf("")
    }

    var checked by rememberSaveable {
        mutableStateOf(true)
    }

    SimpleScaffold(
        onBackClick = onBackClick,
        title = "写微博",
        bottomBar = {
            BottomAppBar(
                actions = {
                    IconButton(onClick = onAddTagClick) {
                        Icon(imageVector = Icons.Default.Tag, contentDescription = "")
                    }
                }
            )
        },
        actions = {
            IconButton(
                enabled = tweetResult !is Result.Loading,
                onClick = {
                    if (body.isNotBlank()) {
                        onSaveClick(
                            body,
                            if (checked) Commentable.Open else Commentable.Close,
                            tags
                        )
                    } else {
                        Toast.makeText(context, "内容不能为空", Toast.LENGTH_SHORT).show()
                    }
                }
            ) {
                Icon(imageVector = Icons.AutoMirrored.Default.Send, contentDescription = "")
            }
        }
    ) {
        Column {
            when (tweetResult) {
                is Result.Error -> {
                    Text(
                        modifier = modifier.padding(16.dp),
                        text = "${tweetResult.exception?.message}",
                        color = MaterialTheme.colorScheme.error
                    )
                }

                Result.Loading -> {
                    LinearProgressIndicator(modifier = modifier.fillMaxWidth())
                }

                is Result.Success -> {
                    onBackClick()
                }

                null -> {}
            }
            LazyColumn(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    OutlinedTextField(
                        value = body,
                        onValueChange = { body = it },
                        modifier = Modifier.fillMaxWidth(),
                        minLines = 8,
                        placeholder = {
                            Text(
                                text = "支持 Markdown 语法",
                                color = MaterialTheme.colorScheme.onBackground.copy(alpha = .5f)
                            )
                        }
                    )
                }

                item {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(text = "开启评论")
                        Switch(checked = checked, onCheckedChange = { checked = it })
                    }
                }

                itemsIndexed(
                    items = tags,
                ) { index, tag ->
                    Row(
                        modifier = Modifier
                            .clip(RoundedCornerShape(16.dp))
                            .background(color = Color.LightGray.copy(alpha = .5f))
                            .clickable { onRemoveTagClick(index) }
                            .padding(16.dp, 8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = "#${tag.name}",
                            color = MaterialTheme.colorScheme.primary
                        )
                        Icon(
                            modifier = Modifier.size(16.dp),
                            imageVector = Icons.Outlined.Delete,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.error
                        )
                    }
                }
            }
        }
    }
}