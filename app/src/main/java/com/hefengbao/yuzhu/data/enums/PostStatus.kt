package com.hefengbao.yuzhu.data.enums

enum class PostStatus(val status: String) {
    Published("published"),
    Draft("draft");

    companion object {
        infix fun from(value: String): PostStatus =
            PostStatus.entries.firstOrNull { it.status == value } ?: Draft
    }
}