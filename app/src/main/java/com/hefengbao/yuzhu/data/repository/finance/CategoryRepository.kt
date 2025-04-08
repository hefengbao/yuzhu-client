package com.hefengbao.yuzhu.data.repository.finance

import com.hefengbao.yuzhu.common.network.Result
import com.hefengbao.yuzhu.data.database.entity.finance.CategoryEntity
import com.hefengbao.yuzhu.data.model.finance.Category
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    suspend fun fetchCategories(authorization: String?): Result<List<Category>>
    fun getCategories(groupIds: IntArray): Flow<List<CategoryEntity>>
    suspend fun insert(entity: CategoryEntity)
    suspend fun insertAll(entities: List<CategoryEntity>)
    suspend fun clear()
}