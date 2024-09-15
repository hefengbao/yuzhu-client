package com.hefengbao.yuzhu.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.hefengbao.yuzhu.data.database.AppDatabase
import com.hefengbao.yuzhu.data.database.entity.CommentEntity
import com.hefengbao.yuzhu.data.model.asCommentEntity
import com.hefengbao.yuzhu.data.network.Network

@OptIn(ExperimentalPagingApi::class)
internal class TweetCommentRemoteMediator(
    private val database: AppDatabase,
    private val network: Network,
    private val authorization: String?,
    private val tweetId: Int
) : RemoteMediator<Int, CommentEntity>() {

    private var lastId: Int? = null

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, CommentEntity>
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

            val items = network.getTweetComments(authorization, tweetId, pageSize, loadKey)

            database.withTransaction {
                database.commentDao().insertAll(items.map { it.asCommentEntity() })
            }

            lastId = items.lastOrNull()?.id

            return MediatorResult.Success(endOfPaginationReached = items.size < pageSize)
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }
}