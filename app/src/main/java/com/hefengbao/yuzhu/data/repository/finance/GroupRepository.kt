package com.hefengbao.yuzhu.data.repository.finance

import com.hefengbao.yuzhu.common.network.Result
import com.hefengbao.yuzhu.data.database.entity.finance.GroupEntity
import com.hefengbao.yuzhu.data.model.finance.Group
import kotlinx.coroutines.flow.Flow

interface GroupRepository {
    suspend fun fetchGroups(authorization: String?): Result<List<Group>>
    fun getGroups(type: String): Flow<List<GroupEntity>>
    suspend fun insert(entity: GroupEntity)
    suspend fun insertAll(entities: List<GroupEntity>)
    suspend fun clear()
}