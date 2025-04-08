package com.hefengbao.yuzhu.data.model.finance

import com.hefengbao.yuzhu.data.database.entity.finance.TransactionEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Transaction(
    val id: Int,
    val uuid: String,
    @SerialName("user_id")
    val userId:Int,
    val account: Account,
    val type: String,
    val date: String,
    val category: Category,
    val notes: String? = null,
    val currency: Currency,
    val amount: Double,
    @SerialName("created_at")
    val createdAt: String,
    @SerialName("updated_at")
    val updatedAt: String,
    @SerialName("deleted_at")
    val deletedAt: String? = null
)

fun Transaction.asTransactionEntity() = TransactionEntity(
    id = id,
    uuid = uuid,
    userId = userId,
    account = account,
    type = type,
    date = date,
    category = category,
    notes = notes,
    currency = currency,
    amount = amount,
    createdAt = createdAt,
    updatedAt = updatedAt,
    deletedAt = deletedAt
)
