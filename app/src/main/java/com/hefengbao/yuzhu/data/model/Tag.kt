package com.hefengbao.yuzhu.data.model

import android.os.Parcelable
import com.hefengbao.yuzhu.data.database.entity.TagEntity
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Tag(
    val id: Int,
    val name: String,
    val slug: String,
    @SerialName("created_at")
    val createdAt: String,
    @SerialName("updated_at")
    val updatedAt: String
) : Parcelable

fun Tag.asTagEntity() = TagEntity(
    id, name, slug, createdAt, updatedAt
)
