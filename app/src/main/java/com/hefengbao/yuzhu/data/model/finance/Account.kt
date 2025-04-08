package com.hefengbao.yuzhu.data.model.finance

import android.os.Parcelable
import com.hefengbao.yuzhu.data.database.entity.finance.AccountEntity
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Account(
    val id: Int,
    @SerialName("user_id")
    val userId: Int,
    val name: String,
    val type: String,
    val balance: Double,
    @SerialName("credit_limit")
    val creditLimit: Double? = null,
    @SerialName("settlement_day")
    val settlementDay: Int? = null,
    val status: Boolean,
    val notes: String? = null,
    @SerialName("created_at")
    val createdAt: String,
    @SerialName("updated_at")
    val updatedAt: String
) : Parcelable

fun Account.asAccountEntity() = AccountEntity(
    id = id,
    userId = userId,
    name = name,
    type = type,
    balance = balance,
    creditLimit = creditLimit,
    settlementDay = settlementDay,
    status = status,
    notes = notes,
    createdAt = createdAt,
    updatedAt = updatedAt,
)
