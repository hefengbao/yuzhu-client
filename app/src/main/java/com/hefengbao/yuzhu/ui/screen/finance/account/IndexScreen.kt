package com.hefengbao.yuzhu.ui.screen.finance.account

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hefengbao.yuzhu.common.network.Result
import com.hefengbao.yuzhu.data.database.entity.finance.AccountEntity
import com.hefengbao.yuzhu.data.database.entity.finance.asAccountModel
import com.hefengbao.yuzhu.data.model.finance.Account
import com.hefengbao.yuzhu.ui.component.SimpleScaffold

@Composable
fun IndexRoute(
    viewModel: IndexViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onAccountSelect: (Account) -> Unit,
){
    val fetchStatus by viewModel.fetchStatus.collectAsState(initial = null)
    val accounts by viewModel.accounts.collectAsState()

    IndexScreen(
        onBackClick = onBackClick,
        onAccountClick = { onAccountSelect(it.asAccountModel()) },
        onRefreshClick = viewModel::fetchAccounts,
        fetchStatus = fetchStatus,
        onResultSuccess = viewModel::insertAccounts,
        accounts = accounts
    )
}

@Composable
private fun IndexScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onAccountClick: (AccountEntity) -> Unit,
    onRefreshClick: () -> Unit,
    fetchStatus: Result<List<Account>>?,
    onResultSuccess: (List<Account>) -> Unit,
    accounts: List<AccountEntity>
) {
    SimpleScaffold(
        onBackClick = onBackClick,
        title = "账户",
        actions = {
            IconButton(
                onClick = onRefreshClick
            ) {
                Icon(imageVector = Icons.Outlined.Refresh, contentDescription = "更新数据")
            }
        }
    ){
        fetchStatus?.let {
            when(fetchStatus){
                is Result.Error -> {
                    Text(fetchStatus.exception.toString())
                }
                Result.Loading -> {
                    LinearProgressIndicator(modifier = modifier.fillMaxWidth())
                }
                is Result.Success -> {
                    onResultSuccess(fetchStatus.data)
                }
            }
            LazyColumn(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                items(
                    items = accounts
                ){ item: AccountEntity ->
                    AccountCard(entity = item, onCardClick = onAccountClick)
                }
            }
        }
    }
}

@Composable
private fun AccountCard(
    modifier: Modifier = Modifier,
    entity: AccountEntity,
    onCardClick: (AccountEntity) -> Unit
) {
    Card(
        modifier = modifier.padding(16.dp),
        onClick = { onCardClick(entity) }
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    text = entity.name,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = entity.type,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}