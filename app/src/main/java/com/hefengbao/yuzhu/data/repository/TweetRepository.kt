package com.hefengbao.yuzhu.data.repository

import androidx.paging.PagingData
import com.hefengbao.yuzhu.common.network.Result
import com.hefengbao.yuzhu.data.database.entity.CommentEntity
import com.hefengbao.yuzhu.data.database.entity.PostEntity
import com.hefengbao.yuzhu.data.model.Comment
import com.hefengbao.yuzhu.data.model.Post
import com.hefengbao.yuzhu.data.model.Tag
import kotlinx.coroutines.flow.Flow

interface TweetRepository {
    fun getTweets(authorization: String?): Flow<PagingData<PostEntity>>
    fun getTweet(id: Int): Flow<PostEntity>
    suspend fun create(
        authorization: String?,
        body: String,
        commentable: String,
        tags: List<Tag>
    ): Result<Post>

    suspend fun insert(entity: PostEntity)
    fun getComments(authorization: String?, articleId: Int): Flow<PagingData<CommentEntity>>
    suspend fun createComments(
        authorization: String?,
        tweetId: Int,
        body: String,
        parentId: Int?
    ): Result<Comment>

    suspend fun insertComment(entity: CommentEntity)
}