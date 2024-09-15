package com.hefengbao.yuzhu.data.model

import com.hefengbao.yuzhu.data.database.entity.CommentEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Comment(
    val id: Int,
    @SerialName("post_id")
    val postId: Int,
    @SerialName("user_id")
    val userId: Int? = null,
    @SerialName("parent_id")
    val parentId: Int? = null,
    val body: String,
    @SerialName("body_html")
    val bodyHtml: String,
    @SerialName("guest_name")
    val guestName: String? = null,
    @SerialName("guest_email")
    val guestEmail: String? = null,
    val status: String,
    @SerialName("created_at")
    val createdAt: String,
    @SerialName("updated_at")
    val updatedAt: String,
    val author: User? = null
)

fun Comment.asCommentEntity() = CommentEntity(
    id,
    postId,
    parentId,
    userId,
    body,
    bodyHtml,
    guestName,
    guestEmail,
    status,
    createdAt,
    updatedAt,
    author
)
