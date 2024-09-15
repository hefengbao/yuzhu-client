package com.hefengbao.yuzhu.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hefengbao.yuzhu.data.model.User

@Entity(tableName = "comments")
data class CommentEntity(
    @PrimaryKey
    val id: Int,
    @ColumnInfo("post_id")
    val postId: Int,
    @ColumnInfo("user_id")
    val userId: Int? = null,
    @ColumnInfo("parent_id")
    val parentId: Int? = null,
    val body: String,
    @ColumnInfo("body_html")
    val bodyHtml: String,
    @ColumnInfo("guest_name")
    val guestName: String? = null,
    @ColumnInfo("guest_email")
    val guestEmail: String? = null,
    val status: String,
    @ColumnInfo("created_at")
    val createdAt: String,
    @ColumnInfo("updated_at")
    val updatedAt: String,
    @Embedded("author_")
    val author: User? = null
)
