package com.hefengbao.yuzhu.ui.screen.finance.category.nav

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.hefengbao.yuzhu.data.model.finance.Category
import com.hefengbao.yuzhu.ui.screen.finance.category.IndexRoute
import java.net.URLDecoder
import java.net.URLEncoder
import kotlin.text.Charsets.UTF_8

private const val typeArg = "type"
private const val base = "finance_category_index"

private const val ROUTE = "$base/{$typeArg}"

internal class FinanceCategoriesArgs(val type: String){
    constructor(savedStateHandle: SavedStateHandle): this(
        URLDecoder.decode(checkNotNull(savedStateHandle[typeArg]), UTF_8.name())
    )
}

fun NavController.navigateToFinanceCategoryIndexScreen(type: String){
    val encodeType = URLEncoder.encode(type, UTF_8.name())

    this.navigate("$base/$encodeType"){
        launchSingleTop = true
    }
}

fun NavGraphBuilder.financeCategoryIndexScreen(
    onBackClick: () -> Unit,
    onCategorySelect: (Category) -> Unit,
){
    composable(
        route = ROUTE,
        arguments = listOf(
            navArgument(typeArg){ type = NavType.StringType}
        )
    ){
        IndexRoute(
            onBackClick = onBackClick,
            onCategorySelect = onCategorySelect
        )
    }
}