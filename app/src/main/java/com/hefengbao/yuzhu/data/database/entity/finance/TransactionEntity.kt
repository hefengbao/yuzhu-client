package com.hefengbao.yuzhu.data.database.entity.finance

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hefengbao.yuzhu.data.model.finance.Account
import com.hefengbao.yuzhu.data.model.finance.Category
import com.hefengbao.yuzhu.data.model.finance.Currency

@Entity("finance_transactions")
data class TransactionEntity(
    @PrimaryKey
    val id: Int,
    val uuid: String,
    @ColumnInfo("user_id")
    val userId:Int,
    @Embedded("account_")
    val account: Account,
    val type: String,
    val date: String,
    @Embedded("category_")
    val category: Category,
    val notes: String? = null,
    @Embedded("currency_")
    val currency: Currency,
    val amount: Double,
    @ColumnInfo("created_at")
    val createdAt: String,
    @ColumnInfo("updated_at")
    val updatedAt: String,
    @ColumnInfo("deleted_at")
    val deletedAt: String? = null
)
