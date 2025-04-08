package com.hefengbao.yuzhu.data.model.finance

import com.hefengbao.yuzhu.data.database.entity.finance.GroupEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Group(
    val id: Int,
    @SerialName("user_id")
    val userId:Int,
    val name: String,
    val type: String,
    @SerialName("created_at")
    val createdAt: String,
    @SerialName("updated_at")
    val updatedAt: String
)

fun Group.asGroupEntity() = GroupEntity(
    id, userId, name, type, createdAt, updatedAt
)
