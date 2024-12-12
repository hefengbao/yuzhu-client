package com.hefengbao.yuzhu.data.enums.post

enum class Commentable(val status: String) {
    Open("open"),
    Close("close");

    companion object {
        infix fun from(value: String): Commentable =
            Commentable.entries.firstOrNull { it.status == value } ?: Open
    }
}