package com.hefengbao.yuzhu.data.database

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.hefengbao.yuzhu.data.database.entity.finance.AccountEntity as FinanceAccountEntity
import com.hefengbao.yuzhu.data.database.entity.finance.CategoryEntity as FinanceCategoryEntity
import com.hefengbao.yuzhu.data.database.entity.finance.GroupEntity as FinanceGroupEntity
import com.hefengbao.yuzhu.data.database.entity.finance.TransactionEntity as FinanceTransactionEntity
import com.hefengbao.yuzhu.data.database.dao.finance.AccountDao as FinanceAccountDao
import com.hefengbao.yuzhu.data.database.dao.finance.CategoryDao as FinanceCategoryDao
import com.hefengbao.yuzhu.data.database.dao.finance.GroupDao as FinanceGroupDao
import com.hefengbao.yuzhu.data.database.dao.finance.TransactionDao as FinanceTransactionDao
import com.hefengbao.yuzhu.data.database.dao.post.CommentDao
import com.hefengbao.yuzhu.data.database.dao.post.PostDao
import com.hefengbao.yuzhu.data.database.dao.post.TagDao
import com.hefengbao.yuzhu.data.database.dao.user.UserDao
import com.hefengbao.yuzhu.data.database.entity.post.PostEntity
import com.hefengbao.yuzhu.data.database.entity.user.UserEntity
import com.hefengbao.yuzhu.data.database.util.IntListConverter
import com.hefengbao.yuzhu.data.database.util.PostCategoryListConverter
import com.hefengbao.yuzhu.data.database.util.PostTagListConverter
import com.hefengbao.yuzhu.data.database.util.StringListConverter
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
        FinanceAccountEntity::class,
        FinanceCategoryEntity::class,
        FinanceGroupEntity::class,
        FinanceTransactionEntity::class,
        PostCategoryEntity::class,
        PostCommentEntity::class,
        PostEntity::class,
        PostTagEntity::class,
        UserEntity::class,
    ],
    version = 2,
    autoMigrations = [
        AutoMigration(from = 1, to = 2),
    ],
    exportSchema = true
)
@TypeConverters(
    IntListConverter::class,
    StringListConverter::class,
    PostCategoryListConverter::class,
    PostTagListConverter::class,
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun financeAccountDao(): FinanceAccountDao
    abstract fun financeCategoryDao(): FinanceCategoryDao
    abstract fun financeGroupDao(): FinanceGroupDao
    abstract fun financeTransactionDao(): FinanceTransactionDao
    abstract fun postCategoryDao(): PostCategoryDao
    abstract fun postCommentDao(): CommentDao
    abstract fun postDao(): PostDao
    abstract fun postTagDao(): TagDao
    abstract fun userDao(): UserDao
}