package com.hefengbao.yuzhu.data.repository

import androidx.paging.PagingData
import com.hefengbao.yuzhu.common.network.Result
import com.hefengbao.yuzhu.data.database.entity.CommentEntity
import com.hefengbao.yuzhu.data.database.entity.PostEntity
import com.hefengbao.yuzhu.data.model.Comment
import kotlinx.coroutines.flow.Flow

interface ArticleRepository {
    fun getArticles(authorization: String?): Flow<PagingData<PostEntity>>
    fun getArticle(id: Int): Flow<PostEntity>
    suspend fun insert(entity: PostEntity)
    fun getComments(authorization: String?, articleId: Int): Flow<PagingData<CommentEntity>>
    suspend fun createComments(
        authorization: String?,
        articleId: Int,
        body: String,
        parentId: Int?
    ): Result<Comment>

    suspend fun insertComment(entity: CommentEntity)
}