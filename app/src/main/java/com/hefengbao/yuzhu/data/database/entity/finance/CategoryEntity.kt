package com.hefengbao.yuzhu.data.database.entity.finance

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hefengbao.yuzhu.data.model.finance.Category
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Entity(tableName = "finance_categories")
data class CategoryEntity(
    @PrimaryKey
    val id: Int,
    @ColumnInfo("user_id")
    val userId: Int,
    @ColumnInfo("group_id")
    val groupId: Int,
    val name: String,
    val items: List<String> = emptyList(),
    @ColumnInfo("created_at")
    val createdAt: String,
    @ColumnInfo("updated_at")
    val updatedAt: String
)

fun CategoryEntity.asCategoryModel() = Category(
    id, userId, groupId, name, items, createdAt, updatedAt
)
