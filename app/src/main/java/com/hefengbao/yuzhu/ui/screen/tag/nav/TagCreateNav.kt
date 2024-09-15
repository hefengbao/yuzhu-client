package com.hefengbao.yuzhu.ui.screen.tag.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.hefengbao.yuzhu.ui.screen.tag.TagCreateRoute

private const val TAG_CREATE_ROUTE = "tag_create"

fun NavController.navigateToTagCreateScreen() = navigate(TAG_CREATE_ROUTE) {
    launchSingleTop = true
}

fun NavGraphBuilder.tagCreateScreen(
    onBackClick: () -> Unit,
) {
    composable(TAG_CREATE_ROUTE) {
        TagCreateRoute(
            onBackClick = onBackClick
        )
    }
}