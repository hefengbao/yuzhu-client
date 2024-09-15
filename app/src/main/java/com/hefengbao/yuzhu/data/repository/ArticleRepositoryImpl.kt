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
import com.hefengbao.yuzhu.data.network.Network
import com.hefengbao.yuzhu.data.paging.ArticleCommentRemoteMediator
import com.hefengbao.yuzhu.data.paging.ArticleRemoteMediator
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class ArticleRepositoryImpl @Inject constructor(
    private val database: AppDatabase,
    private val network: Network,
    private val dao: PostDao,
    private val commentDao: CommentDao
) : ArticleRepository, SafeApiCall {

    override fun getArticles(authorization: String?): Flow<PagingData<PostEntity>> = Pager(
        config = PagingConfig(pageSize = 15),
        pagingSourceFactory = { dao.getArticles() },
        remoteMediator = ArticleRemoteMediator(database, network, AppStatus.accessToken!!)
    ).flow

    override fun getArticle(id: Int): Flow<PostEntity> = dao.getArticle(id)

    override suspend fun insert(entity: PostEntity) = dao.insert(entity)

    override fun getComments(
        authorization: String?,
        articleId: Int
    ): Flow<PagingData<CommentEntity>> = Pager(
        config = PagingConfig(pageSize = 15),
        pagingSourceFactory = { commentDao.getComments(articleId) },
        remoteMediator = ArticleCommentRemoteMediator(database, network, authorization, articleId)
    ).flow

    override suspend fun createComments(
        authorization: String?,
        articleId: Int,
        body: String,
        parentId: Int?
    ): Result<Comment> = safeApiCall {
        network.createArticleComment(authorization, articleId, body, parentId)
    }

    override suspend fun insertComment(entity: CommentEntity) = commentDao.insert(entity)

}