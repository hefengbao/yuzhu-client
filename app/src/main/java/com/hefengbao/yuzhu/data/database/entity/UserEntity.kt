package com.hefengbao.yuzhu.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey
    val id: Int,
    val name: String?,
    val avatar: String?,
    val bio: String?,
    val role: String
)
