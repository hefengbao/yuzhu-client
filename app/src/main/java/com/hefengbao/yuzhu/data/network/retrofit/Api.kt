package com.hefengbao.yuzhu.data.network.retrofit

import com.hefengbao.yuzhu.data.model.AuthToken
import com.hefengbao.yuzhu.data.model.Category
import com.hefengbao.yuzhu.data.model.Comment
import com.hefengbao.yuzhu.data.model.Tag
import com.hefengbao.yuzhu.data.model.User
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import com.hefengbao.yuzhu.data.model.Post as NetworkPost

interface Api {

    @FormUrlEncoded
    @POST("auth/login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("user_agent") userAgent: String,
    ): AuthToken

    @POST("auth/logout")
    suspend fun logout(
        @Header("Authorization") authorization: String?
    ): Response<Unit>

    @GET("me")
    suspend fun me(
        @Header("Authorization") authorization: String?
    ): User

    @GET("articles")
    suspend fun articles(
        @Header("Authorization") authorization: String?,
        @Query("page_size") pageSize: Int,
        @Query("key") key: Int? = null,
    ): List<NetworkPost>

    @FormUrlEncoded
    @POST("articles")
    suspend fun createArticle(
        @Header("Authorization") authorization: String?,
        @Field("title") title: String,
        @Field("slug") slug: String?,
        @Field("body") body: String,
        @Field("excerpt") excerpt: String,
        @Field("commentable") commentable: String,
        @Field("status") status: String,
        @Field("published_at") publishedAt: String? = null,
        @Field("categories[]") categories: List<Int>? = null,
        @Field("tags[]") tags: List<Int>? = null,
    ): NetworkPost

    @GET("articles/{articleId}/comments")
    suspend fun articleComments(
        @Header("Authorization") authorization: String?,
        @Path("articleId") articleId: Int,
        @Query("page_size") pageSize: Int,
        @Query("key") key: Int? = null,
    ): List<Comment>

    @FormUrlEncoded
    @POST("articles/{articleId}/comments")
    suspend fun createArticleComment(
        @Header("Authorization") authorization: String?,
        @Path("articleId") articleId: Int,
        @Field("body") body: String,
        @Field("parent_id") parentId: Int? = null
    ): Comment


    @GET("tweets")
    suspend fun tweets(
        @Header("Authorization") authorization: String?,
        @Query("page_size") pageSize: Int,
        @Query("key") key: Int? = null,
    ): List<NetworkPost>

    @FormUrlEncoded
    @POST("tweets")
    suspend fun createTweet(
        @Header("Authorization") authorization: String?,
        @Field("body") body: String,
        @Field("commentable") commentable: String,
        @Field("tags[]") tags: List<Int>? = null,
    ): NetworkPost

    @GET("tweets/{tweedId}/comments")
    suspend fun tweetComments(
        @Header("Authorization") authorization: String?,
        @Path("tweedId") tweedId: Int,
        @Query("page_size") pageSize: Int,
        @Query("key") key: Int? = null,
    ): List<Comment>

    @FormUrlEncoded
    @POST("tweets/{tweedId}/comments")
    suspend fun createTweetComment(
        @Header("Authorization") authorization: String?,
        @Path("tweedId") tweedId: Int,
        @Field("body") body: String,
        @Field("parent_id") parentId: Int? = null
    ): Comment

    @GET("pages")
    suspend fun pages(
        @Header("Authorization") authorization: String?,
        @Query("page_size") pageSize: Int,
        @Query("key") key: Int? = null,
    ): List<NetworkPost>

    @FormUrlEncoded
    @POST("pages")
    suspend fun createPage(
        @Header("Authorization") authorization: String?,
        @Field("title") title: String,
        @Field("slug") slug: String?,
        @Field("body") body: String,
        @Field("commentable") commentable: String,
    ): NetworkPost

    @GET("categories")
    suspend fun categories(
        @Header("Authorization") authorization: String?,
    ): List<Category>

    @FormUrlEncoded
    @POST("categories")
    suspend fun createCategory(
        @Header("Authorization") authorization: String?,
        @Field("name") name: String,
    ): Category

    @GET("tags")
    suspend fun tags(
        @Header("Authorization") authorization: String?,
    ): List<Tag>

    @FormUrlEncoded
    @POST("tags")
    suspend fun createTag(
        @Header("Authorization") authorization: String?,
        @Field("name") name: String,
    ): Tag
}