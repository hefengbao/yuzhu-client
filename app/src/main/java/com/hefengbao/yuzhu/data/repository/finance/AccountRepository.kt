package com.hefengbao.yuzhu.data.repository.finance

import com.hefengbao.yuzhu.common.network.Result
import com.hefengbao.yuzhu.data.database.entity.finance.AccountEntity
import com.hefengbao.yuzhu.data.model.finance.Account
import kotlinx.coroutines.flow.Flow

interface AccountRepository {
    suspend fun fetchAccounts(authorization: String?): Result<List<Account>>
    fun getAccounts(): Flow<List<AccountEntity>>
    suspend fun insert(entity: AccountEntity)
    suspend fun insertAll(entities: List<AccountEntity>)
    suspend fun clear()
}