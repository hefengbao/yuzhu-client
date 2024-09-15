package com.hefengbao.yuzhu.data.repository

import com.hefengbao.yuzhu.common.network.Result
import com.hefengbao.yuzhu.common.network.SafeApiCall
import com.hefengbao.yuzhu.data.database.dao.TagDao
import com.hefengbao.yuzhu.data.database.entity.TagEntity
import com.hefengbao.yuzhu.data.model.Tag
import com.hefengbao.yuzhu.data.network.Network
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TagRepositoryImpl @Inject constructor(
    private val network: Network,
    private val tagDao: TagDao,
) : TagRepository, SafeApiCall {
    override suspend fun syncTags(authentication: String?): Result<List<Tag>> = safeApiCall {
        network.getTags(authentication)
    }

    override suspend fun create(authentication: String?, name: String): Result<Tag> = safeApiCall {
        network.createTag(authentication, name)
    }

    override suspend fun insert(entity: TagEntity) = tagDao.insert(entity)

    override suspend fun insertAll(entities: List<TagEntity>) = tagDao.insertAll(entities)

    override fun getTags(): Flow<List<TagEntity>> = tagDao.getTags()
}