package com.hefengbao.yuzhu.data.network

import com.hefengbao.yuzhu.data.model.AuthToken
import com.hefengbao.yuzhu.data.model.Category
import com.hefengbao.yuzhu.data.model.Comment
import com.hefengbao.yuzhu.data.model.Post
import com.hefengbao.yuzhu.data.model.Tag
import com.hefengbao.yuzhu.data.model.User
import retrofit2.Response

interface Network {
    suspend fun login(email: String, password: String, userAgent: String): AuthToken
    suspend fun logout(authorization: String?): Response<Unit>
    suspend fun me(authorization: String?): User
    suspend fun getArticles(authorization: String?, pageSize: Int, key: Int? = null): List<Post>
    suspend fun createArticle(
        authorization: String?,
        title: String,
        slug: String?,
        body: String,
        excerpt: String,
        commentable: String,
        status: String,
        publishedAt: String? = null,
        categories: List<Int>? = null,
        tags: List<Int>? = null,
    ): Post

    suspend fun getArticleComments(
        authorization: String?,
        articleId: Int,
        pageSize: Int,
        key: Int? = null
    ): List<Comment>

    suspend fun createArticleComment(
        authorization: String?,
        articleId: Int,
        body: String,
        parentId: Int? = null,
    ): Comment

    suspend fun getTweets(authorization: String?, pageSize: Int, key: Int? = null): List<Post>
    suspend fun createTweet(
        authorization: String?,
        body: String,
        commentable: String,
        tags: List<Int>? = null,
    ): Post

    suspend fun getTweetComments(
        authorization: String?,
        tweetId: Int,
        pageSize: Int,
        key: Int? = null
    ): List<Comment>

    suspend fun createTweetComment(
        authorization: String?,
        tweetId: Int,
        body: String,
        parentId: Int? = null
    ): Comment

    suspend fun getPages(authorization: String?, pageSize: Int, key: Int? = null): List<Post>
    suspend fun createPage(
        authorization: String?,
        title: String,
        slug: String?,
        body: String,
        commentable: String,
    ): Post

    suspend fun getCategories(authorization: String?): List<Category>
    suspend fun createCategory(authorization: String?, name: String): Category
    suspend fun getTags(authorization: String?): List<Tag>
    suspend fun createTag(authorization: String?, name: String): Tag
}