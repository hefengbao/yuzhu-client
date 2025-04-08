package com.hefengbao.yuzhu.data.database.entity.finance

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hefengbao.yuzhu.data.model.finance.Account
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Entity("finance_accounts")
data class AccountEntity(
    @PrimaryKey
    val id: Int,
    @ColumnInfo("user_id")
    val userId: Int,
    val name: String,
    val type: String,
    val balance: Double,
    @ColumnInfo("credit_limit")
    val creditLimit: Double? = null,
    @ColumnInfo("settlement_day")
    val settlementDay: Int? = null,
    val status: Boolean,
    val notes: String? = null,
    @ColumnInfo("created_at")
    val createdAt: String,
    @ColumnInfo("updated_at")
    val updatedAt: String
)

fun AccountEntity.asAccountModel() = Account(
    id, userId, name, type, balance, creditLimit, settlementDay, status, notes, createdAt, updatedAt
)
