package com.hefengbao.yuzhu.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hefengbao.yuzhu.data.model.Tag

@Entity(tableName = "tags")
data class TagEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val slug: String,
    @ColumnInfo("created_at")
    val createdAt: String,
    @ColumnInfo("updated_at")
    val updatedAt: String
)


fun TagEntity.asTag() = Tag(
    id, name, slug, createdAt, updatedAt
)