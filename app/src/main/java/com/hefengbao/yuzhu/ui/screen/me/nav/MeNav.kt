package com.hefengbao.yuzhu.ui.screen.me.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.hefengbao.yuzhu.ui.screen.me.MeRoute

const val meGraphRoutePattern = "me_graph"
const val ME_ROUTE = "me"

fun NavController.navigateToMeGraph(navOptions: NavOptions? = null) {
    this.navigate(meGraphRoutePattern, navOptions)
}

fun NavGraphBuilder.meGraph(
    onSettingsClick: () -> Unit,
    nestedGraphs: NavGraphBuilder.() -> Unit
) {
    navigation(
        route = meGraphRoutePattern,
        startDestination = ME_ROUTE
    ) {
        composable(ME_ROUTE) {
            MeRoute(
                onSettingsClick = onSettingsClick
            )
        }
        nestedGraphs()
    }
}