package com.hefengbao.yuzhu.data.network.retrofit

import com.hefengbao.yuzhu.data.datastore.AppPreference
import com.hefengbao.yuzhu.data.model.AuthToken
import com.hefengbao.yuzhu.data.model.Category
import com.hefengbao.yuzhu.data.model.Comment
import com.hefengbao.yuzhu.data.model.Post
import com.hefengbao.yuzhu.data.model.Tag
import com.hefengbao.yuzhu.data.model.User
import com.hefengbao.yuzhu.data.network.Network
import com.hefengbao.yuzhu.di.ApplicationScope
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkImpl @Inject constructor(
    networkJson: Json,
    okhttpCallFactory: Call.Factory,
    preference: AppPreference,
    @ApplicationScope
    private val applicationScope: CoroutineScope,
) : Network {

    private lateinit var networkApi: Api

    init {
        applicationScope.launch {
            preference.appStatus.collectLatest { app ->
                if (!app.domain.isNullOrEmpty()) {
                    networkApi = Retrofit.Builder()
                        .baseUrl("${app.domain}/api/v1/")
                        .callFactory(okhttpCallFactory)
                        .addConverterFactory(
                            networkJson.asConverterFactory("application/json".toMediaType())
                        ).build()
                        .create(Api::class.java)
                }
            }
        }
    }

    override suspend fun login(email: String, password: String, userAgent: String): AuthToken =
        networkApi.login(email, password, userAgent)

    override suspend fun logout(authorization: String?): Response<Unit> =
        networkApi.logout(authorization)

    override suspend fun me(authorization: String?): User = networkApi.me(authorization)

    override suspend fun getArticles(authorization: String?, pageSize: Int, key: Int?): List<Post> =
        networkApi.articles(authorization, pageSize, key)

    override suspend fun createArticle(
        authorization: String?,
        title: String,
        slug: String?,
        body: String,
        excerpt: String,
        commentable: String,
        status: String,
        publishedAt: String?,
        categories: List<Int>?,
        tags: List<Int>?
    ): Post = networkApi.createArticle(
        authorization,
        title,
        slug,
        body,
        excerpt,
        commentable,
        status,
        publishedAt,
        categories,
        tags
    )

    override suspend fun getArticleComments(
        authorization: String?,
        id: Int,
        pageSize: Int,
        key: Int?
    ): List<Comment> =
        networkApi.articleComments(authorization, id, pageSize, key)

    override suspend fun createArticleComment(
        authorization: String?,
        articleId: Int,
        body: String,
        parentId: Int?
    ): Comment = networkApi.createArticleComment(
        authorization, articleId, body, parentId
    )

    override suspend fun getTweets(authorization: String?, pageSize: Int, key: Int?): List<Post> =
        networkApi.tweets(authorization, pageSize, key)

    override suspend fun createTweet(
        authorization: String?,
        body: String,
        commentable: String,
        tags: List<Int>?
    ): Post =
        networkApi.createTweet(authorization, body, commentable, tags)

    override suspend fun getTweetComments(
        authorization: String?,
        tweetId: Int,
        pageSize: Int,
        key: Int?
    ): List<Comment> =
        networkApi.tweetComments(authorization, tweetId, pageSize, key)

    override suspend fun createTweetComment(
        authorization: String?,
        tweetId: Int,
        body: String,
        parentId: Int?
    ): Comment = networkApi.createTweetComment(authorization, tweetId, body, parentId)

    override suspend fun getPages(authorization: String?, pageSize: Int, key: Int?): List<Post> =
        networkApi.pages(authorization, pageSize, key)

    override suspend fun createPage(
        authorization: String?,
        title: String,
        slug: String?,
        body: String,
        commentable: String
    ): Post = networkApi.createPage(authorization, title, slug, body, commentable)

    override suspend fun getCategories(authorization: String?): List<Category> =
        networkApi.categories(authorization)

    override suspend fun createCategory(authorization: String?, name: String): Category =
        networkApi.createCategory(authorization, name)

    override suspend fun getTags(authorization: String?): List<Tag> = networkApi.tags(authorization)

    override suspend fun createTag(authorization: String?, name: String): Tag =
        networkApi.createTag(authorization, name)
}