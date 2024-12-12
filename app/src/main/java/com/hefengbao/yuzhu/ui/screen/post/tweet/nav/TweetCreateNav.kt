package com.hefengbao.yuzhu.ui.screen.post.tweet.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.hefengbao.yuzhu.ui.screen.post.tweet.TweetCreateRoute

private const val TWEET_CREATE_ROUTE = "tweet_create"

fun NavController.navigateToTweetCreateScreen() = navigate(TWEET_CREATE_ROUTE) {
    launchSingleTop = true
}

fun NavGraphBuilder.tweetCreateScreen(
    onBackClick: () -> Unit,
    onAddTagClick: () -> Unit
) {
    composable(TWEET_CREATE_ROUTE) {
        TweetCreateRoute(
            backStackEntry = it,
            onBackClick = onBackClick,
            onAddTagClick = onAddTagClick
        )
    }
}