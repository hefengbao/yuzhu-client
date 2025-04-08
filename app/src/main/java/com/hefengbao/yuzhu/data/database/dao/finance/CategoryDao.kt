package com.hefengbao.yuzhu.data.database.dao.finance

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hefengbao.yuzhu.data.database.entity.finance.CategoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: CategoryEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(entities: List<CategoryEntity>)

    @Query("select * from finance_categories where group_id in (:groupIds) order by group_id, id asc")
    fun list(groupIds: IntArray): Flow<List<CategoryEntity>>

    @Query("delete from finance_categories")
    suspend fun clear()
}