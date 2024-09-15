package com.hefengbao.yuzhu.ui.screen.tag

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Sync
import androidx.compose.material.icons.outlined.Create
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hefengbao.yuzhu.data.database.entity.TagEntity
import com.hefengbao.yuzhu.data.database.entity.asTag
import com.hefengbao.yuzhu.data.model.Tag
import com.hefengbao.yuzhu.ui.component.SimpleScaffold

@Composable
fun TagIndexRoute(
    viewModel: TagIndexViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onCreateClick: () -> Unit,
    onItemClick: (Tag) -> Unit
) {
    val tags by viewModel.tags.collectAsState(initial = emptyList())

    TagIndexScreen(
        onBackClick = onBackClick,
        onCreateClick = onCreateClick,
        onItemClick = onItemClick,
        onSync = { viewModel.sync() },
        tags = tags
    )
}

@Composable
private fun TagIndexScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onItemClick: (Tag) -> Unit,
    onCreateClick: () -> Unit,
    onSync: () -> Unit,
    tags: List<TagEntity>
) {
    SimpleScaffold(
        onBackClick = onBackClick,
        title = "标签",
        actions = {
            IconButton(onClick = onSync) {
                Icon(imageVector = Icons.Default.Sync, contentDescription = null)
            }
        },
        floatingActionButton = {
            IconButton(onClick = onCreateClick) {
                Icon(imageVector = Icons.Outlined.Create, contentDescription = null)
            }
        }
    ) {
        LazyColumn {
            items(
                items = tags
            ) { tag ->
                Text(
                    text = tag.name,
                    modifier = modifier
                        .clickable {
                            Log.i("TagIndexScreen", "$tag")
                            onItemClick(tag.asTag())
                        }
                        .padding(16.dp, 8.dp)
                        .fillMaxWidth()
                )
            }
        }
    }
}