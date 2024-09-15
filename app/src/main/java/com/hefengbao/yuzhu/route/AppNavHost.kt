package com.hefengbao.yuzhu.route

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.hefengbao.yuzhu.ui.screen.article.nav.articleGraph
import com.hefengbao.yuzhu.ui.screen.article.nav.articleShowScreen
import com.hefengbao.yuzhu.ui.screen.article.nav.navigateToArticleShowScreen
import com.hefengbao.yuzhu.ui.screen.me.nav.meGraph
import com.hefengbao.yuzhu.ui.screen.settings.nav.aboutScreen
import com.hefengbao.yuzhu.ui.screen.settings.nav.navigateToAboutScreen
import com.hefengbao.yuzhu.ui.screen.settings.nav.navigateToPrivacyScreen
import com.hefengbao.yuzhu.ui.screen.settings.nav.navigateToSettingsGraph
import com.hefengbao.yuzhu.ui.screen.settings.nav.privacyScreen
import com.hefengbao.yuzhu.ui.screen.settings.nav.settingsGraph
import com.hefengbao.yuzhu.ui.screen.tag.nav.navigateToTagCreateScreen
import com.hefengbao.yuzhu.ui.screen.tag.nav.navigateToTagGraph
import com.hefengbao.yuzhu.ui.screen.tag.nav.tagCreateScreen
import com.hefengbao.yuzhu.ui.screen.tag.nav.tagGraph
import com.hefengbao.yuzhu.ui.screen.tweet.nav.navigateToTweetCreateScreen
import com.hefengbao.yuzhu.ui.screen.tweet.nav.navigateToTweetShowScreen
import com.hefengbao.yuzhu.ui.screen.tweet.nav.tweetCreateScreen
import com.hefengbao.yuzhu.ui.screen.tweet.nav.tweetGraph
import com.hefengbao.yuzhu.ui.screen.tweet.nav.tweetGraphRoutePattern
import com.hefengbao.yuzhu.ui.screen.tweet.nav.tweetShowScreen

@Composable
fun AppNavHost(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = tweetGraphRoutePattern
    ) {
        articleGraph(
            onItemClick = { navController.navigateToArticleShowScreen(it) },
            nestedGraphs = {
                articleShowScreen(
                    onBackClick = navController::navigateUp
                )
            }
        )
        meGraph(
            onSettingsClick = { navController.navigateToSettingsGraph() },
            nestedGraphs = {

            }
        )
        settingsGraph(
            onAboutClick = { navController.navigateToAboutScreen() },
            onBackClick = navController::navigateUp,
            onPrivacyClick = { navController.navigateToPrivacyScreen() },
            nestedGraphs = {
                aboutScreen(
                    onBackClick = navController::navigateUp
                )
                privacyScreen(
                    onBackClick = navController::navigateUp
                )
            }
        )
        tagGraph(
            onBackClick = navController::navigateUp,
            onCreateClick = { navController.navigateToTagCreateScreen() },
            onItemClick = {
                navController.previousBackStackEntry
                    ?.savedStateHandle
                    ?.set("tag", it)
                Log.i("AppNavHost", "$it")
                navController.popBackStack()
            },
            nestedGraphs = {
                tagCreateScreen(
                    onBackClick = navController::navigateUp
                )
            }
        )
        tweetGraph(
            onCreateClick = { navController.navigateToTweetCreateScreen() },
            onItemClick = { navController.navigateToTweetShowScreen(it) },
            nestedGraphs = {
                tweetShowScreen(
                    onBackClick = navController::navigateUp
                )
                tweetCreateScreen(
                    onBackClick = navController::navigateUp,
                    onAddTagClick = { navController.navigateToTagGraph() }
                )
            }
        )
    }
}