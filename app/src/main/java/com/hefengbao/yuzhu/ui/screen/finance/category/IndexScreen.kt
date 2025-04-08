package com.hefengbao.yuzhu.ui.screen.finance.category

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hefengbao.yuzhu.common.network.Result
import com.hefengbao.yuzhu.data.database.entity.finance.CategoryEntity
import com.hefengbao.yuzhu.data.database.entity.finance.GroupEntity
import com.hefengbao.yuzhu.data.database.entity.finance.asCategoryModel
import com.hefengbao.yuzhu.data.model.finance.Category
import com.hefengbao.yuzhu.data.model.finance.Group
import com.hefengbao.yuzhu.ui.component.SimpleScaffold

@Composable
fun IndexRoute(
    viewModel: IndexViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onCategorySelect: (Category) -> Unit,
){
    val fetchStatus by viewModel.fetchStatus.collectAsState(initial = null)
    val fetchGroupsStatus by viewModel.fetchGroupsStatus.collectAsState(null)
    val groups by viewModel.groups.collectAsState()
    val categories by viewModel.categories.collectAsState(emptyList())

    LaunchedEffect(groups) {
        viewModel.getCategories(groups.map { it.id }.toIntArray())
    }

    IndexScreen(
        onBackClick = onBackClick,
        onCategorySelect = onCategorySelect,
        onRefreshClick = {
            viewModel.fetchGroups()
            viewModel.fetchCategories()
        },
        fetchStatus = fetchStatus,
        fetchGroupsStatus = fetchGroupsStatus,
        onFetchStatusSuccess = viewModel::insertCategories,
        onFetchGroupsStatusSuccess = viewModel::insertGroups,
        groups = groups,
        categories = categories
    )
}

@Composable
private fun IndexScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onCategorySelect: (Category) -> Unit,
    onRefreshClick: () -> Unit,
    fetchStatus: Result<List<Category>>?,
    fetchGroupsStatus: Result<List<Group>>?,
    onFetchStatusSuccess: (List<Category>) -> Unit,
    onFetchGroupsStatusSuccess: (List<Group>) -> Unit,
    groups: List<GroupEntity>,
    categories: List<CategoryEntity>
) {
    SimpleScaffold(
        onBackClick = onBackClick,
        title = "类型",
        actions = {
            IconButton(
                onClick = onRefreshClick
            ) {
                Icon(imageVector = Icons.Outlined.Refresh, contentDescription = null)
            }
        }
    ){
        Column {
            fetchStatus?.let {
                when(fetchStatus){
                    is Result.Error -> {
                        Text("${fetchStatus.exception?.message}")
                    }
                    Result.Loading -> {
                        LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
                    }
                    is Result.Success -> {
                        onFetchStatusSuccess(fetchStatus.data)
                    }
                }
            }
            fetchGroupsStatus?.let {
                when(fetchGroupsStatus){
                    is Result.Error -> {
                        Text("${fetchGroupsStatus.exception?.message}")
                    }
                    Result.Loading -> {
                        LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
                    }
                    is Result.Success -> {
                        onFetchGroupsStatusSuccess(fetchGroupsStatus.data)
                    }
                }
            }
        }

        if (groups.isNotEmpty()){
            var selectGroupId by remember {
                mutableIntStateOf(groups[0].id)
            }

            Row(
                modifier = modifier.fillMaxSize()
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxHeight()
                        .weight(4f)
                ) {
                    items(
                        items = groups
                    ){ item ->
                        Text(
                            text = item.name,
                            modifier = Modifier.fillMaxWidth()
                                .clickable { selectGroupId = item.id }
                                .background(
                                    if (selectGroupId == item.id)
                                        MaterialTheme.colorScheme.primary
                                    else
                                        MaterialTheme.colorScheme.background
                                )
                                .padding(vertical = 8.dp, horizontal = 16.dp),
                            color =  if (selectGroupId == item.id)
                                MaterialTheme.colorScheme.onPrimary
                            else
                                MaterialTheme.colorScheme.onBackground
                        )
                    }
                }

                Spacer(modifier = Modifier.weight(0.5f))

                LazyColumn(
                    modifier = Modifier.fillMaxHeight()
                        .weight(6f),
                ) {
                    items(
                        items = categories.filter { item -> item.groupId == selectGroupId }
                    ){ item: CategoryEntity ->
                        Card(
                            modifier = Modifier.fillMaxWidth().padding(16.dp, 8.dp),
                            onClick =  { onCategorySelect(item.asCategoryModel()) },
                        ) {
                            Column(
                                modifier = Modifier.padding(16.dp),
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Text(
                                    text = item.name,
                                    style = MaterialTheme.typography.titleMedium
                                )
                                Text(
                                    text = item.items.joinToString(","),
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}