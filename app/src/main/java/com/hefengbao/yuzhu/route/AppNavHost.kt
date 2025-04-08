package com.hefengbao.yuzhu.route

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.hefengbao.yuzhu.ui.screen.finance.account.nav.financeAccountIndexScreen
import com.hefengbao.yuzhu.ui.screen.finance.account.nav.navigateToFinanceAccountIndexScreen
import com.hefengbao.yuzhu.ui.screen.finance.category.nav.financeCategoryIndexScreen
import com.hefengbao.yuzhu.ui.screen.finance.category.nav.navigateToFinanceCategoryIndexScreen
import com.hefengbao.yuzhu.ui.screen.finance.transaction.nav.financeGraph
import com.hefengbao.yuzhu.ui.screen.finance.transaction.nav.financeTransactionCreateScreen
import com.hefengbao.yuzhu.ui.screen.finance.transaction.nav.navigateToFinanceGraph
import com.hefengbao.yuzhu.ui.screen.finance.transaction.nav.navigateToFinanceTransactionCreateScreen
import com.hefengbao.yuzhu.ui.screen.post.article.nav.articleGraph
import com.hefengbao.yuzhu.ui.screen.post.article.nav.articleShowScreen
import com.hefengbao.yuzhu.ui.screen.post.article.nav.navigateToArticleShowScreen
import com.hefengbao.yuzhu.ui.screen.post.tag.nav.navigateToTagCreateScreen
import com.hefengbao.yuzhu.ui.screen.post.tag.nav.navigateToTagGraph
import com.hefengbao.yuzhu.ui.screen.post.tag.nav.tagCreateScreen
import com.hefengbao.yuzhu.ui.screen.post.tag.nav.tagGraph
import com.hefengbao.yuzhu.ui.screen.post.tweet.nav.navigateToTweetCreateScreen
import com.hefengbao.yuzhu.ui.screen.post.tweet.nav.navigateToTweetShowScreen
import com.hefengbao.yuzhu.ui.screen.post.tweet.nav.tweetCreateScreen
import com.hefengbao.yuzhu.ui.screen.post.tweet.nav.tweetGraph
import com.hefengbao.yuzhu.ui.screen.post.tweet.nav.tweetGraphRoutePattern
import com.hefengbao.yuzhu.ui.screen.post.tweet.nav.tweetShowScreen
import com.hefengbao.yuzhu.ui.screen.settings.nav.aboutScreen
import com.hefengbao.yuzhu.ui.screen.settings.nav.navigateToAboutScreen
import com.hefengbao.yuzhu.ui.screen.settings.nav.navigateToPrivacyScreen
import com.hefengbao.yuzhu.ui.screen.settings.nav.navigateToSettingsGraph
import com.hefengbao.yuzhu.ui.screen.settings.nav.privacyScreen
import com.hefengbao.yuzhu.ui.screen.settings.nav.settingsGraph
import com.hefengbao.yuzhu.ui.screen.user.me.nav.meGraph

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
        financeGraph(
            onBackClick = navController::navigateUp,
            onCreateClick = navController::navigateToFinanceTransactionCreateScreen,
            onStatClick = {}
        ){
            financeAccountIndexScreen(
                onBackClick = navController::navigateUp,
                onAccountSelect = {
                    navController.previousBackStackEntry
                        ?.savedStateHandle
                        ?.set("account", it)
                    navController.popBackStack()
                }
            )

            financeCategoryIndexScreen(
                onBackClick = navController::navigateUp,
                onCategorySelect = {
                    navController.previousBackStackEntry
                        ?.savedStateHandle
                        ?.set("category", it)
                    navController.popBackStack()
                }
            )

            financeTransactionCreateScreen(
                onBackClick = navController::navigateUp,
                onSelectAccountClick = navController::navigateToFinanceAccountIndexScreen,
                onSelectCategoryClick = navController::navigateToFinanceCategoryIndexScreen
            )
        }
        meGraph(
            onFinanceClick = navController::navigateToFinanceGraph,
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