package com.hefengbao.yuzhu.data.repository.finance

import com.hefengbao.yuzhu.common.network.Result
import com.hefengbao.yuzhu.common.network.SafeApiCall
import com.hefengbao.yuzhu.data.database.dao.finance.TransactionDao
import com.hefengbao.yuzhu.data.database.entity.finance.TransactionEntity
import com.hefengbao.yuzhu.data.model.finance.Transaction
import com.hefengbao.yuzhu.data.network.Network
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TransactionRepositoryImpl @Inject constructor(
    private val network: Network,
    private val dao: TransactionDao
) : TransactionRepository, SafeApiCall {
    override suspend fun fetchTransactions(
        authorization: String?,
        startDate: String,
        endDate: String
    ): Result<List<Transaction>> = safeApiCall {
        network.getFinanceTransactions(authorization, startDate, endDate)
    }

    override fun getTransactions(
        startDate: String,
        endDate: String
    ): Flow<List<TransactionEntity>> = dao.list(startDate, endDate)

    override suspend fun createTransaction(
        authorization: String?,
        accountId: Int,
        type: String,
        date: String,
        categoryId: Int,
        amount: Double,
        notes: String?
    ): Result<Transaction> = safeApiCall {
        network.createFinanceTransaction(
            authorization,
            accountId,
            type,
            date,
            categoryId,
            amount,
            notes
        )
    }

    override suspend fun insert(entity: TransactionEntity) = dao.insert(entity)

    override suspend fun insertAll(entities: List<TransactionEntity>) = dao.insertAll(entities)
}