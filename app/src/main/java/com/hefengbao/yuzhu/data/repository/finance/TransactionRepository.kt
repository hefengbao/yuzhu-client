package com.hefengbao.yuzhu.data.repository.finance

import com.hefengbao.yuzhu.common.network.Result
import com.hefengbao.yuzhu.data.database.entity.finance.TransactionEntity
import com.hefengbao.yuzhu.data.model.finance.Transaction
import kotlinx.coroutines.flow.Flow

interface TransactionRepository {
    suspend fun fetchTransactions(
        authorization: String?,
        startDate: String,
        endDate: String
    ): Result<List<Transaction>>

    fun getTransactions(startDate: String, endDate: String): Flow<List<TransactionEntity>>

    suspend fun createTransaction(
        authorization: String?,
        accountId: Int,
        type: String,
        date: String,
        categoryId: Int,
        amount: Double,
        notes: String?
    ): Result<Transaction>

    suspend fun insert(entity: TransactionEntity)

    suspend fun insertAll(entities: List<TransactionEntity>)
}