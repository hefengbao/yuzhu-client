package com.hefengbao.yuzhu.data.model.finance

import android.os.Parcelable
import com.hefengbao.yuzhu.data.database.entity.finance.CategoryEntity
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Category(
    val id: Int,
    @SerialName("user_id")
    val userId: Int,
    @SerialName("group_id")
    val groupId: Int,
    val name: String,
    val items: List<String> = emptyList(),
    @SerialName("created_at")
    val createdAt: String,
    @SerialName("updated_at")
    val updatedAt: String
) : Parcelable

fun Category.asCategoryEntity() = CategoryEntity(
    id = id,
    userId = userId,
    groupId = groupId,
    name = name,
    items = items,
    createdAt = createdAt,
    updatedAt = createdAt
)
