package com.hefengbao.yuzhu.ui.screen.tweet.nav

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.hefengbao.yuzhu.ui.screen.tweet.TweetShowRoute
import java.net.URLDecoder
import java.net.URLEncoder
import kotlin.text.Charsets.UTF_8

private const val tweetShowArgId = "tweet_id"
private const val BASE = "tweet_show"
private const val TWEET_SHOW_ROUTE = "$BASE/{$tweetShowArgId}"

internal class TweetShowArgs(val tweetId: String) {
    constructor(savedStateHandle: SavedStateHandle) :
            this(URLDecoder.decode(checkNotNull(savedStateHandle[tweetShowArgId]), UTF_8.name()))
}

fun NavController.navigateToTweetShowScreen(id: Int) {
    val encodedId = URLEncoder.encode(id.toString(), UTF_8.name())
    this.navigate("$BASE/$encodedId") {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.tweetShowScreen(
    onBackClick: () -> Unit,
) {
    composable(
        route = TWEET_SHOW_ROUTE,
        arguments = listOf(
            navArgument(tweetShowArgId) { type = NavType.StringType }
        )
    ) {
        TweetShowRoute(
            onBackClick = onBackClick
        )
    }
}