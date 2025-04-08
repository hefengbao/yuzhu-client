package com.hefengbao.yuzhu.data.repository.finance

import com.hefengbao.yuzhu.common.network.Result
import com.hefengbao.yuzhu.common.network.SafeApiCall
import com.hefengbao.yuzhu.data.database.dao.finance.CategoryDao
import com.hefengbao.yuzhu.data.database.entity.finance.CategoryEntity
import com.hefengbao.yuzhu.data.model.finance.Category
import com.hefengbao.yuzhu.data.network.Network
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val network: Network,
    private val dao: CategoryDao
) : CategoryRepository, SafeApiCall {
    override suspend fun fetchCategories(authorization: String?): Result<List<Category>> =
        safeApiCall {
            network.getFinanceCategories(authorization)
        }

    override fun getCategories(groupIds: IntArray): Flow<List<CategoryEntity>> = dao.list(groupIds)

    override suspend fun insert(entity: CategoryEntity) = dao.insert(entity)

    override suspend fun insertAll(entities: List<CategoryEntity>) = dao.insertAll(entities)

    override suspend fun clear() = dao.clear()
}