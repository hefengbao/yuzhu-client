package com.hefengbao.yuzhu.data.enums.finance

enum class FinanceType(val type: String) {
    Expense("expense"),
    Income("income");

    companion object {
        infix fun from(value: String): FinanceType =
            FinanceType.entries.firstOrNull { it.type == value } ?: Expense

        infix fun label(value: String): String = when(from(value)){
            Expense -> "支出"
            Income -> "收入"
        }
    }
}