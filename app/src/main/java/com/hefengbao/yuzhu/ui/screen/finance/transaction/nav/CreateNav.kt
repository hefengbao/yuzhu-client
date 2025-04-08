package com.hefengbao.yuzhu.ui.screen.finance.transaction.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.hefengbao.yuzhu.ui.screen.finance.transaction.CreateRoute

private const val ROUTE = "finance_transaction_create"

fun NavController.navigateToFinanceTransactionCreateScreen(){
    this.navigate(ROUTE){
        launchSingleTop = true
    }
}

fun NavGraphBuilder.financeTransactionCreateScreen(
    onBackClick: () -> Unit,
    onSelectAccountClick: () -> Unit,
    onSelectCategoryClick: (type: String) -> Unit,
){
    composable(ROUTE){
        CreateRoute(
            backStackEntry = it,
            onBackClick = onBackClick,
            onSelectAccountClick = onSelectAccountClick,
            onSelectCategoryClick = onSelectCategoryClick
        )
    }
}