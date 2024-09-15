package com.hefengbao.yuzhu.data.enums

enum class PostType(val type: String) {
    Article("article"),
    Tweet("tweet"),
    Page("page");

    companion object {
        infix fun from(value: String): PostType =
            PostType.entries.firstOrNull { it.type == value } ?: Article
    }
}