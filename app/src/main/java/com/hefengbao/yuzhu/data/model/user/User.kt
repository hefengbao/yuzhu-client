package com.hefengbao.yuzhu.data.model.user

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: Int,
    val name: String?,
    val avatar: String?,
    val bio: String?,
    val role: String
)
