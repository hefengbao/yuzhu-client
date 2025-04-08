package com.hefengbao.yuzhu.ui.screen.finance.account.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.hefengbao.yuzhu.data.model.finance.Account
import com.hefengbao.yuzhu.ui.screen.finance.account.IndexRoute

private const val ROUTE = "finance_account_index"

fun NavController.navigateToFinanceAccountIndexScreen(){
    this.navigate(ROUTE){
        launchSingleTop = true
    }
}

fun NavGraphBuilder.financeAccountIndexScreen(
    onBackClick: () -> Unit,
    onAccountSelect: (Account) -> Unit
){
    composable(ROUTE){
        IndexRoute(
            onBackClick = onBackClick,
            onAccountSelect = onAccountSelect
        )
    }
}