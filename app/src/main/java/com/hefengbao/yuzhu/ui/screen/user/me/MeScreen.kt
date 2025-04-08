package com.hefengbao.yuzhu.ui.screen.user.me

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountBalanceWallet
import androidx.compose.material.icons.outlined.Money
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hefengbao.yuzhu.data.model.user.UserData
import com.hefengbao.yuzhu.ui.component.Avatar

@Composable
fun MeRoute(
    viewModel: MeViewModel = hiltViewModel(),
    onFinanceClick: () -> Unit,
    onSettingsClick: () -> Unit,
) {
    val userData by viewModel.userData.collectAsState(initial = null)
    MeScreen(
        onFinanceClick = onFinanceClick,
        onSettingsClick = onSettingsClick,
        userData = userData,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MeScreen(
    modifier: Modifier = Modifier,
    onFinanceClick: () -> Unit,
    onSettingsClick: () -> Unit,
    userData: UserData?,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "我的")
                },
                actions = {
                    IconButton(onClick = onSettingsClick) {
                        Icon(imageVector = Icons.Outlined.Settings, contentDescription = "设置")
                    }
                }
            )
        },
    ) { paddingValues ->
        LazyVerticalGrid(
            modifier = modifier.padding(paddingValues),
            columns = GridCells.Fixed(2)
        ) {
            userData?.user?.let { user ->
                item(
                    span = {
                        GridItemSpan(2)
                    }
                ) {
                    Card(
                        modifier = Modifier.padding(16.dp),
                        onClick = { /*TODO*/ }
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            Avatar(
                                userId = user.id,
                                userAvatar = user.avatar,
                                size = 64.dp,
                                onClick = {}
                            )
                            Column(
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Text(text = user.name ?: "")
                                Text(
                                    text = user.bio ?: "",
                                    style = MaterialTheme.typography.labelMedium.copy(
                                        color = MaterialTheme.colorScheme.onPrimary.copy(
                                            alpha = .5f
                                        )
                                    )
                                )
                            }
                        }
                    }
                }
            }
            item{
                ItemCard(
                    title = "财务",
                    icon = Icons.Outlined.AccountBalanceWallet,
                    onClick = onFinanceClick
                )
            }
        }
    }
}

@Composable
private fun ItemCard(
    modifier: Modifier = Modifier,
    title: String,
    icon: ImageVector,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = modifier.padding(16.dp),
    ) {
        Column(
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Image(
                imageVector = icon,
                contentDescription = title
            )
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
            )
        }
    }
}