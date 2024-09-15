package com.hefengbao.yuzhu.ui.screen.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavDestination
import com.hefengbao.yuzhu.R
import com.hefengbao.yuzhu.common.util.NetworkMonitor
import com.hefengbao.yuzhu.route.AppNavHost
import com.hefengbao.yuzhu.ui.screen.home.components.SimpleNavigationBar
import com.hefengbao.yuzhu.ui.screen.home.components.SimpleNavigationBarItem
import com.hefengbao.yuzhu.ui.screen.home.nav.TopLevelDestination

@Composable
fun OneApp(
    networkMonitor: NetworkMonitor,
    appState: YuzhuAppState = rememberNilAppState(
        networkMonitor = networkMonitor,
    )
) {

    val snackbarHostState = remember { SnackbarHostState() }
    val isOffline by appState.isOffline.collectAsStateWithLifecycle()
    val notConnectedMessage = stringResource(R.string.not_connected)

    LaunchedEffect(isOffline) {
        if (isOffline) {
            snackbarHostState.showSnackbar(
                message = notConnectedMessage,
                duration = SnackbarDuration.Indefinite,
            )
        }
    }
    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(0.dp)
                .consumeWindowInsets(WindowInsets(0.dp))
                .windowInsetsPadding(
                    WindowInsets.safeDrawing.only(
                        WindowInsetsSides.Horizontal,
                    ),
                )
                .weight(1f),
        ) {
            AppNavHost(appState.navController)
        }
        if (appState.shouldShowBottomBar) {
            BottomBar(
                destinations = appState.topLevelDestinations,
                onNavigateToDestination = appState::navigateToTopLevelDestination,
                currentDestination = appState.currentDestination,
                modifier = Modifier.testTag("BottomBar")
            )
        }
    }
}

@Composable
private fun BottomBar(
    destinations: List<TopLevelDestination>,
    onNavigateToDestination: (TopLevelDestination) -> Unit,
    currentDestination: NavDestination?,
    modifier: Modifier = Modifier,
) {
    SimpleNavigationBar(modifier) {
        destinations.forEach { destination ->
            val selected = currentDestination?.route == destination.route
            val label = stringResource(id = destination.iconTextId)
            SimpleNavigationBarItem(
                selected = selected,
                onClick = { onNavigateToDestination(destination) },
                icon = {
                    val icon = if (selected) {
                        destination.selectedIcon
                    } else {
                        destination.unselectedIcon
                    }

                    Icon(
                        imageVector = icon,
                        contentDescription = label
                    )
                },
                label = { Text(text = label) }
            )
        }
    }
}