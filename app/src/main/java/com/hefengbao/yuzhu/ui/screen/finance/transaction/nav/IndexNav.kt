package com.hefengbao.yuzhu.ui.screen.finance.transaction.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.hefengbao.yuzhu.ui.screen.finance.transaction.TransactionIndexRoute

private const val ROUTE_GRAPH = "finance_graph"
private const val ROUTE = "finance_transaction_index"

fun NavController.navigateToFinanceGraph(){
    this.navigate(ROUTE_GRAPH){
        launchSingleTop = true
    }
}

fun NavGraphBuilder.financeGraph(
    onBackClick: () -> Unit,
    onCreateClick: () -> Unit,
    onStatClick: () -> Unit,
    nestedGraphs: NavGraphBuilder.() -> Unit
){
    navigation(
        startDestination = ROUTE,
        route = ROUTE_GRAPH
    ){
        composable(ROUTE){
            TransactionIndexRoute(
                onBackClick = onBackClick,
                onCreateClick = onCreateClick,
                onStatClick = onStatClick
            )
        }

        nestedGraphs()
    }
}