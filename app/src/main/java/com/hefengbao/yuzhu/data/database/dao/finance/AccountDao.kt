package com.hefengbao.yuzhu.data.database.dao.finance

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hefengbao.yuzhu.data.database.entity.finance.AccountEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AccountDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: AccountEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(entity: List<AccountEntity>)

    @Query("select * from finance_accounts where status = 1")
    fun list(): Flow<List<AccountEntity>>

    @Query("delete from finance_accounts")
    suspend fun clear()
}