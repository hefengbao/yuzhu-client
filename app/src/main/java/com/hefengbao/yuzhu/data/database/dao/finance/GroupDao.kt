package com.hefengbao.yuzhu.data.database.dao.finance

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hefengbao.yuzhu.data.database.entity.finance.GroupEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GroupDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: GroupEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(entities: List<GroupEntity>)

    @Query("select * from finance_groups where type = :type order by id asc")
    fun list(type: String): Flow<List<GroupEntity>>

    @Query("delete from finance_groups")
    suspend fun clear()
}