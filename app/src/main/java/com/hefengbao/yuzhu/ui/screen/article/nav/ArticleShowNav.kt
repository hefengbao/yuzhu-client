package com.hefengbao.yuzhu.ui.screen.article.nav

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.hefengbao.yuzhu.ui.screen.article.ArticleShowRoute
import java.net.URLDecoder
import java.net.URLEncoder
import kotlin.text.Charsets.UTF_8

private const val articleShowArgId = "article_id"
private const val BASE = "article_show"
private const val ARTICLE_SHOW_ROUTE = "$BASE/{$articleShowArgId}"

internal class ArticleShowArgs(val articleId: String) {
    constructor(savedStateHandle: SavedStateHandle) :
            this(URLDecoder.decode(checkNotNull(savedStateHandle[articleShowArgId]), UTF_8.name()))
}

fun NavController.navigateToArticleShowScreen(id: Int) {
    val encodedId = URLEncoder.encode(id.toString(), UTF_8.name())
    this.navigate("$BASE/$encodedId") {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.articleShowScreen(
    onBackClick: () -> Unit,
) {
    composable(
        route = ARTICLE_SHOW_ROUTE,
        arguments = listOf(
            navArgument(articleShowArgId) { type = NavType.StringType }
        )
    ) {
        ArticleShowRoute(
            onBackClick = onBackClick
        )
    }
}