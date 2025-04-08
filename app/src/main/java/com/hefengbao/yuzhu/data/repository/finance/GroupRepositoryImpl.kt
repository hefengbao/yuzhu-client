package com.hefengbao.yuzhu.data.repository.finance

import com.hefengbao.yuzhu.common.network.Result
import com.hefengbao.yuzhu.common.network.SafeApiCall
import com.hefengbao.yuzhu.data.database.dao.finance.GroupDao
import com.hefengbao.yuzhu.data.database.entity.finance.GroupEntity
import com.hefengbao.yuzhu.data.model.finance.Group
import com.hefengbao.yuzhu.data.network.Network
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GroupRepositoryImpl @Inject constructor(
    private val network: Network,
    private val dao: GroupDao
) : GroupRepository, SafeApiCall {
    override suspend fun fetchGroups(authorization: String?): Result<List<Group>> = safeApiCall {
        network.getFinanceGroups(authorization)
    }

    override fun getGroups(type: String): Flow<List<GroupEntity>> = dao.list(type)

    override suspend fun insert(entity: GroupEntity) = dao.insert(entity)

    override suspend fun insertAll(entities: List<GroupEntity>) = dao.insertAll(entities)

    override suspend fun clear() = dao.clear()
}