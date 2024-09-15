package com.hefengbao.yuzhu.data.model

import com.hefengbao.yuzhu.data.database.entity.PostEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Post(
    val id: Int,
    val title: String?,
    val slug: String,
    val url: String,
    val excerpt: String?,
    val body: String,
    @SerialName("body_html")
    val bodyHtml: String,
    val type: String,
    val status: String,
    val commentable: String,
    @SerialName("comment_count")
    val commentCount: Int = 0,
    @SerialName("pinned_at")
    val pinnedAt: String?,
    @SerialName("published_at")
    val publishedAt: String?,
    @SerialName("created_at")
    val createdAt: String,
    @SerialName("updated_at")
    val updatedAt: String,
    val author: User,
    val categories: List<Category> = emptyList(),
    val tags: List<Tag> = emptyList(),
    @SerialName("can_edit")
    val canEdit: Boolean,
    @SerialName("can_delete")
    val canDelete: Boolean,
)

fun Post.asPostEntity() = PostEntity(
    id,
    title,
    slug,
    url,
    excerpt,
    body,
    bodyHtml,
    type,
    status,
    commentable,
    commentCount,
    pinnedAt,
    publishedAt,
    createdAt,
    updatedAt,
    author,
    categories,
    tags,
    canEdit,
    canDelete
)