package com.hefengbao.yuzhu.data.repository.finance

import com.hefengbao.yuzhu.common.network.Result
import com.hefengbao.yuzhu.common.network.SafeApiCall
import com.hefengbao.yuzhu.data.database.dao.finance.AccountDao
import com.hefengbao.yuzhu.data.database.entity.finance.AccountEntity
import com.hefengbao.yuzhu.data.model.finance.Account
import com.hefengbao.yuzhu.data.network.Network
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(
    private val network: Network,
    private val dao: AccountDao
) : AccountRepository, SafeApiCall {
    override suspend fun fetchAccounts(authorization: String?): Result<List<Account>> =
        safeApiCall {
            network.getFinanceAccounts(authorization)
        }

    override fun getAccounts(): Flow<List<AccountEntity>> = dao.list()

    override suspend fun insert(entity: AccountEntity) = dao.insert(entity)

    override suspend fun insertAll(entities: List<AccountEntity>) = dao.insertAll(entities)

    override suspend fun clear() = dao.clear()
}