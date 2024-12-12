package com.hefengbao.yuzhu.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.hefengbao.yuzhu.data.database.dao.post.CommentDao
import com.hefengbao.yuzhu.data.database.dao.post.PostDao
import com.hefengbao.yuzhu.data.database.dao.post.TagDao
import com.hefengbao.yuzhu.data.database.dao.user.UserDao
import com.hefengbao.yuzhu.data.database.entity.post.PostEntity
import com.hefengbao.yuzhu.data.database.entity.user.UserEntity
import com.hefengbao.yuzhu.data.database.util.PostCategoryListConverter
import com.hefengbao.yuzhu.data.database.util.PostTagListConverter
import com.hefengbao.yuzhu.data.database.dao.post.CategoryDao as PostCategoryDao
import com.hefengbao.yuzhu.data.database.entity.post.CategoryEntity as PostCategoryEntity
import com.hefengbao.yuzhu.data.database.entity.post.CommentEntity as PostCommentEntity
import com.hefengbao.yuzhu.data.database.entity.post.TagEntity as PostTagEntity

/**
 *  entities 数组中添加 data class 或其中的 data class 发生任何变化， 先 version + 1, 然后再 Build。
 *  app/schemas 目录下，保存 exportSchema 数据
 */
@Database(
    entities = [
        PostCategoryEntity::class,
        PostCommentEntity::class,
        PostEntity::class,
        PostTagEntity::class,
        UserEntity::class,
    ],
    version = 1,
    autoMigrations = [

    ],
    exportSchema = true
)
@TypeConverters(
    PostCategoryListConverter::class,
    PostTagListConverter::class,
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun postCategoryDao(): PostCategoryDao
    abstract fun postCommentDao(): CommentDao
    abstract fun postDao(): PostDao
    abstract fun postTagDao(): TagDao
    abstract fun userDao(): UserDao
}