package com.hefengbao.yuzhu.data.database.entity.finance

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Entity("finance_groups")
data class GroupEntity(
    @PrimaryKey
    val id: Int,
    @ColumnInfo("user_id")
    val userId:Int,
    val name: String,
    val type: String,
    @ColumnInfo("created_at")
    val createdAt: String,
    @ColumnInfo("updated_at")
    val updatedAt: String
)
