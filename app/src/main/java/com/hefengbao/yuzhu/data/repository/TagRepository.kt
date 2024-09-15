package com.hefengbao.yuzhu.data.repository

import com.hefengbao.yuzhu.common.network.Result
import com.hefengbao.yuzhu.data.database.entity.TagEntity
import com.hefengbao.yuzhu.data.model.Tag
import kotlinx.coroutines.flow.Flow

interface TagRepository {
    suspend fun syncTags(authentication: String?): Result<List<Tag>>
    suspend fun create(authentication: String?, name: String): Result<Tag>
    suspend fun insert(entity: TagEntity)
    suspend fun insertAll(entities: List<TagEntity>)
    fun getTags(): Flow<List<TagEntity>>
}