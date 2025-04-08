package com.hefengbao.yuzhu.ui.screen.finance.transaction

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.PieChartOutline
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hefengbao.yuzhu.ui.component.SimpleScaffold

@Composable
fun TransactionIndexRoute(
    viewModel: IndexViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onCreateClick: () -> Unit,
    onStatClick: () -> Unit,
) {
    TransactionScreen(
        onBackClick = onBackClick,
        onCreateClick = onCreateClick,
        onStatClick = onStatClick
    )
}

@Composable
private fun TransactionScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onCreateClick: () -> Unit,
    onStatClick: () -> Unit,
) {
    SimpleScaffold(
        onBackClick = onBackClick,
        title = "收支列表",
        actions = {
            IconButton(onClick = onStatClick) {
                Icon(imageVector = Icons.Outlined.PieChartOutline, contentDescription = null)
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onCreateClick
            ) {
                Icon(imageVector = Icons.Outlined.Add, contentDescription = null)
            }
        }
    ){
        Column (
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

        }
    }
}