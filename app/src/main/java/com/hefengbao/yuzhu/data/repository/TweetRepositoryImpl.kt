package com.hefengbao.yuzhu.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.hefengbao.yuzhu.AppStatus
import com.hefengbao.yuzhu.common.network.Result
import com.hefengbao.yuzhu.common.network.SafeApiCall
import com.hefengbao.yuzhu.data.database.AppDatabase
import com.hefengbao.yuzhu.data.database.dao.CommentDao
import com.hefengbao.yuzhu.data.database.dao.PostDao
import com.hefengbao.yuzhu.data.database.entity.CommentEntity
import com.hefengbao.yuzhu.data.database.entity.PostEntity
import com.hefengbao.yuzhu.data.model.Comment
import com.hefengbao.yuzhu.data.model.Post
import com.hefengbao.yuzhu.data.model.Tag
import com.hefengbao.yuzhu.data.network.Network
import com.hefengbao.yuzhu.data.paging.TweetCommentRemoteMediator
import com.hefengbao.yuzhu.data.paging.TweetRemoteMediator
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class TweetRepositoryImpl @Inject constructor(
    private val database: AppDatabase,
    private val network: Network,
    private val dao: PostDao,
    private val commentDao: CommentDao
) : TweetRepository, SafeApiCall {
    override fun getTweets(authorization: String?): Flow<PagingData<PostEntity>> = Pager(
        config = PagingConfig(pageSize = 15),
        pagingSourceFactory = { dao.getTweets() },
        remoteMediator = TweetRemoteMediator(database, network, authorization)
    ).flow

    override fun getTweet(id: Int): Flow<PostEntity> = dao.getTweet(id)

    override suspend fun create(
        authorization: String?,
        body: String,
        commentable: String,
        tags: List<Tag>
    ): Result<Post> = safeApiCall {
        network.createTweet(authorization, body, commentable, tags.map { it.id })
    }

    override suspend fun insert(entity: PostEntity) = dao.insert(entity)

    override fun getComments(
        authorization: String?,
        articleId: Int,
    ): Flow<PagingData<CommentEntity>> = Pager(
        config = PagingConfig(15),
        pagingSourceFactory = { commentDao.getComments(articleId) },
        remoteMediator = TweetCommentRemoteMediator(
            database,
            network,
            AppStatus.accessToken,
            articleId
        )
    ).flow

    override suspend fun createComments(
        authorization: String?,
        tweetId: Int,
        body: String,
        parentId: Int?
    ): Result<Comment> = safeApiCall {
        network.createTweetComment(authorization, tweetId, body, parentId)
    }

    override suspend fun insertComment(entity: CommentEntity) = commentDao.insert(entity)
}