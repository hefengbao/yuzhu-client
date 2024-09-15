package com.hefengbao.yuzhu.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.hefengbao.yuzhu.data.database.AppDatabase
import com.hefengbao.yuzhu.data.database.entity.PostEntity
import com.hefengbao.yuzhu.data.model.asPostEntity
import com.hefengbao.yuzhu.data.network.Network

@OptIn(ExperimentalPagingApi::class)
internal class ArticleRemoteMediator(
    private val database: AppDatabase,
    private val network: Network,
    private val authorization: String
) : RemoteMediator<Int, PostEntity>() {

    private var lastId: Int? = null

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PostEntity>
    ): MediatorResult {
        val loadKey = when (loadType) {
            LoadType.REFRESH -> null
            LoadType.PREPEND -> {
                return MediatorResult.Success(endOfPaginationReached = true)
            }

            LoadType.APPEND -> {
                if (state.pages.last().data.isEmpty()) {
                    return MediatorResult.Success(endOfPaginationReached = false)
                } else {
                    lastId
                }
            }
        }

        val pageSize = when (loadType) {
            LoadType.REFRESH -> state.config.initialLoadSize
            else -> state.config.pageSize
        }

        return try {
            val items = network.getArticles(authorization, pageSize, loadKey)

            database.withTransaction {
                database.postDao().insertPosts(items.map { it.asPostEntity() })
            }

            lastId = items.lastOrNull()?.id

            return MediatorResult.Success(endOfPaginationReached = items.size < pageSize)
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }
}