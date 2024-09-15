package com.hefengbao.yuzhu.ui.screen.tag.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.hefengbao.yuzhu.data.model.Tag
import com.hefengbao.yuzhu.ui.screen.tag.TagIndexRoute

private const val TAG_ROUTE = "tag_index"
private const val TAG_GRAPH = "tag_graph"

fun NavController.navigateToTagGraph() = navigate(TAG_ROUTE) {
    launchSingleTop = true
}

fun NavGraphBuilder.tagGraph(
    onBackClick: () -> Unit,
    onCreateClick: () -> Unit,
    onItemClick: (Tag) -> Unit,
    nestedGraphs: NavGraphBuilder.() -> Unit
) {
    navigation(
        startDestination = TAG_ROUTE,
        route = TAG_GRAPH
    ) {
        composable(TAG_ROUTE) {
            TagIndexRoute(
                onBackClick = onBackClick,
                onCreateClick = onCreateClick,
                onItemClick = onItemClick
            )
        }

        nestedGraphs()
    }
}