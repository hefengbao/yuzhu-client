package com.hefengbao.yuzhu.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.hefengbao.yuzhu.data.database.dao.CategoryDao
import com.hefengbao.yuzhu.data.database.dao.CommentDao
import com.hefengbao.yuzhu.data.database.dao.PostDao
import com.hefengbao.yuzhu.data.database.dao.TagDao
import com.hefengbao.yuzhu.data.database.dao.UserDao
import com.hefengbao.yuzhu.data.database.entity.CategoryEntity
import com.hefengbao.yuzhu.data.database.entity.CommentEntity
import com.hefengbao.yuzhu.data.database.entity.PostEntity
import com.hefengbao.yuzhu.data.database.entity.TagEntity
import com.hefengbao.yuzhu.data.database.entity.UserEntity
import com.hefengbao.yuzhu.data.database.util.CategoryListConverter
import com.hefengbao.yuzhu.data.database.util.TagListConverter

/**
 *  entities 数组中添加 data class 或其中的 data class 发生任何变化， 先 version + 1, 然后再 Build。
 *  app/schemas 目录下，保存 exportSchema 数据
 */
@Database(
    entities = [
        CategoryEntity::class,
        CommentEntity::class,
        PostEntity::class,
        TagEntity::class,
        UserEntity::class,
    ],
    version = 1,
    autoMigrations = [

    ],
    exportSchema = true
)
@TypeConverters(
    CategoryListConverter::class,
    TagListConverter::class,
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
    abstract fun commentDao(): CommentDao
    abstract fun postDao(): PostDao
    abstract fun tagDao(): TagDao
    abstract fun userDao(): UserDao
}