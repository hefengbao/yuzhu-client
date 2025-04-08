package com.hefengbao.yuzhu.data.model.finance

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Currency(
    val id: Int,
    val name: String,
    val code: String,
    val symbol: String? = null,
    val createdAt: String,
    @SerialName("updated_at")
    val updatedAt: String
)
