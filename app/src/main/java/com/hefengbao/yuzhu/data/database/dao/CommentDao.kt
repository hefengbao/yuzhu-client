package com.hefengbao.yuzhu.data.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hefengbao.yuzhu.data.database.entity.CommentEntity

@Dao
interface CommentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: CommentEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(entities: List<CommentEntity>)

    @Query("select * from comments where post_id = :postId order by id asc")
    fun getComments(postId: Int): PagingSource<Int, CommentEntity>
}