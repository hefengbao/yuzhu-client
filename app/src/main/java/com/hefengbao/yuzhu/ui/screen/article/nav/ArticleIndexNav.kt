package com.hefengbao.yuzhu.ui.screen.article.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.hefengbao.yuzhu.ui.screen.article.ArticleIndexRoute

const val articleGraphRoutePattern = "article_graph"
const val ARTICLE_INDEX_ROUTE = "article_index"

fun NavController.navigateToArticleGraph(navOptions: NavOptions? = null) {
    this.navigate(articleGraphRoutePattern, navOptions)
}

fun NavGraphBuilder.articleGraph(
    onItemClick: (Int) -> Unit,
    nestedGraphs: NavGraphBuilder.() -> Unit
) {
    navigation(
        route = articleGraphRoutePattern,
        startDestination = ARTICLE_INDEX_ROUTE
    ) {
        composable(ARTICLE_INDEX_ROUTE) {
            ArticleIndexRoute(
                onItemClick = onItemClick
            )
        }
        nestedGraphs()
    }
}