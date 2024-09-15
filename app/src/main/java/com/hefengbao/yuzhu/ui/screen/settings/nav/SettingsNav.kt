package com.hefengbao.yuzhu.ui.screen.settings.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.hefengbao.yuzhu.ui.screen.settings.SettingsRoute

private const val ROUTE_SETTINGS = "settings"
private const val ROUTE_SETTINGS_GRAPH = "settings_graph"

fun NavController.navigateToSettingsGraph() {
    this.navigate(ROUTE_SETTINGS_GRAPH)
}

fun NavGraphBuilder.settingsGraph(
    onAboutClick: () -> Unit,
    onBackClick: () -> Unit,
    onPrivacyClick: () -> Unit,
    nestedGraphs: NavGraphBuilder.() -> Unit
) {
    navigation(
        startDestination = ROUTE_SETTINGS,
        route = ROUTE_SETTINGS_GRAPH
    ) {
        composable(ROUTE_SETTINGS) {
            SettingsRoute(
                onAboutClick = onAboutClick,
                onBackClick = onBackClick,
                onPrivacyClick = onPrivacyClick,
            )
        }
        nestedGraphs()
    }
}