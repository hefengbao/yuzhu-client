package com.hefengbao.yuzhu.ui.screen.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.hefengbao.yuzhu.common.util.NetworkMonitor
import com.hefengbao.yuzhu.ui.screen.article.nav.ARTICLE_INDEX_ROUTE
import com.hefengbao.yuzhu.ui.screen.article.nav.navigateToArticleGraph
import com.hefengbao.yuzhu.ui.screen.home.nav.TopLevelDestination
import com.hefengbao.yuzhu.ui.screen.me.nav.ME_ROUTE
import com.hefengbao.yuzhu.ui.screen.me.nav.navigateToMeGraph
import com.hefengbao.yuzhu.ui.screen.tweet.nav.TWEET_INDEX_ROUTE
import com.hefengbao.yuzhu.ui.screen.tweet.nav.navigateToTweetGraph
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

@Composable
fun rememberNilAppState(
    networkMonitor: NetworkMonitor,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberNavController(),
): YuzhuAppState {
    return remember(networkMonitor, coroutineScope, navController) {
        YuzhuAppState(networkMonitor, coroutineScope, navController)
    }
}

@Stable
class YuzhuAppState(
    val networkMonitor: NetworkMonitor,
    val coroutineScope: CoroutineScope,
    val navController: NavHostController
) {
    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    private val currentTopLevelDestination: TopLevelDestination?
        @Composable get() = when (currentDestination?.route) {
            TWEET_INDEX_ROUTE -> TopLevelDestination.TWEET
            ARTICLE_INDEX_ROUTE -> TopLevelDestination.ARTICLE
            ME_ROUTE -> TopLevelDestination.ME
            else -> null
        }

    val topLevelDestinations: List<TopLevelDestination> = TopLevelDestination.entries

    val shouldShowBottomBar: Boolean
        @Composable get() = currentTopLevelDestination != null

    val isOffline = networkMonitor.isOnline
        .map(Boolean::not)
        .stateIn(
            scope = coroutineScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = false,
        )

    fun navigateToTopLevelDestination(topLevelDestination: TopLevelDestination) {
        val topLevelNavOptions = navOptions {
            // Pop up to the start destination of the graph to
            // avoid building up a large stack of destinations
            // on the back stack as users select items
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            // Avoid multiple copies of the same destination when
            // reselecting the same item
            launchSingleTop = true
            // Restore state when reselecting a previously selected item
            restoreState = true
        }

        when (topLevelDestination) {
            TopLevelDestination.TWEET -> navController.navigateToTweetGraph(topLevelNavOptions)
            TopLevelDestination.ARTICLE -> navController.navigateToArticleGraph(topLevelNavOptions)
            TopLevelDestination.ME -> navController.navigateToMeGraph(topLevelNavOptions)
        }
    }
}