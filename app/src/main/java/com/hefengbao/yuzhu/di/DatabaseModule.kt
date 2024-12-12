package com.hefengbao.yuzhu.di

import android.content.Context
import androidx.room.Room.databaseBuilder
import com.hefengbao.yuzhu.common.Constant
import com.hefengbao.yuzhu.data.database.AppDatabase
import com.hefengbao.yuzhu.data.database.dao.post.CategoryDao
import com.hefengbao.yuzhu.data.database.dao.post.CommentDao
import com.hefengbao.yuzhu.data.database.dao.post.PostDao
import com.hefengbao.yuzhu.data.database.dao.post.TagDao
import com.hefengbao.yuzhu.data.database.dao.user.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun providesAppDatabase(
        @ApplicationContext context: Context,
    ): AppDatabase = databaseBuilder(
        context,
        AppDatabase::class.java,
        Constant.DB_NAME,
    ).build()

    @Provides
    @Singleton
    fun providesCommentDao(
        database: AppDatabase
    ): CommentDao = database.postCommentDao()

    @Provides
    @Singleton
    fun providesPostCategoryDao(
        database: AppDatabase
    ): CategoryDao = database.postCategoryDao()

    @Provides
    @Singleton
    fun providesPostDao(
        database: AppDatabase
    ): PostDao = database.postDao()

    @Provides
    @Singleton
    fun providesTagDao(
        database: AppDatabase
    ): TagDao = database.postTagDao()

    @Provides
    @Singleton
    fun providesUserDao(
        database: AppDatabase
    ): UserDao = database.userDao()
}