package com.hefengbao.yuzhu.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hefengbao.yuzhu.data.model.Category
import com.hefengbao.yuzhu.data.model.Tag
import com.hefengbao.yuzhu.data.model.User

@Entity(tableName = "posts")
data class PostEntity(
    @PrimaryKey
    val id: Int,
    val title: String?,
    val slug: String,
    val url: String,
    val excerpt: String?,
    val body: String,
    @ColumnInfo("body_html")
    val bodyHtml: String,
    val type: String,
    val status: String,
    val commentable: String,
    @ColumnInfo("comment_count")
    val commentCount: Int,
    @ColumnInfo("pinned_at")
    val pinnedAt: String?,
    @ColumnInfo("published_at")
    val publishedAt: String?,
    @ColumnInfo("created_at")
    val createdAt: String,
    @ColumnInfo("updated_at")
    val updatedAt: String,
    @Embedded(prefix = "author_")
    val author: User,
    val categories: List<Category> = emptyList(),
    val tags: List<Tag> = emptyList(),
    @ColumnInfo("can_edit")
    val canEdit: Boolean,
    @ColumnInfo("can_delete")
    val canDelete: Boolean,
)