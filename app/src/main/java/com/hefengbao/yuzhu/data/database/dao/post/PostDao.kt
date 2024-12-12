package com.hefengbao.yuzhu.data.database.dao.post

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hefengbao.yuzhu.data.database.entity.post.PostEntity
import com.hefengbao.yuzhu.data.enums.post.PostType
import kotlinx.coroutines.flow.Flow

@Dao
interface PostDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: PostEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPosts(entities: List<PostEntity>)

    @Query("select * from posts where type = :type and id = :id")
    fun getArticle(id: Int, type: String = PostType.Article.type): Flow<PostEntity>

    @Query("select * from posts where type = :type and id = :id")
    fun getTweet(id: Int, type: String = PostType.Tweet.type): Flow<PostEntity>

    @Query("SELECT * FROM posts WHERE type = :type ORDER BY id DESC")
    fun getArticles(type: String = PostType.Article.type): PagingSource<Int, PostEntity>

    @Query("SELECT * FROM posts WHERE type = :type ORDER BY id DESC")
    fun getTweets(type: String = PostType.Tweet.type): PagingSource<Int, PostEntity>
}