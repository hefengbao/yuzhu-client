package com.hefengbao.yuzhu.ui.screen.tweet.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.hefengbao.yuzhu.ui.screen.tweet.TweetIndexRoute

const val tweetGraphRoutePattern = "tweet_graph"
const val TWEET_INDEX_ROUTE = "tweet_index"

fun NavController.navigateToTweetGraph(navOptions: NavOptions) {
    this.navigate(tweetGraphRoutePattern, navOptions)
}

fun NavGraphBuilder.tweetGraph(
    onCreateClick: () -> Unit,
    onItemClick: (Int) -> Unit,
    nestedGraphs: NavGraphBuilder.() -> Unit,
) {
    navigation(
        route = tweetGraphRoutePattern,
        startDestination = TWEET_INDEX_ROUTE
    ) {
        composable(TWEET_INDEX_ROUTE) {
            TweetIndexRoute(
                onCreateClick = onCreateClick,
                onItemClick = onItemClick
            )
        }
        nestedGraphs()
    }
}