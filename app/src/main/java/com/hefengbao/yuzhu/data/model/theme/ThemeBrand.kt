package com.hefengbao.yuzhu.data.model.theme

enum class ThemeBrand {
    DEFAULT,
    ANDROID;

    companion object {
        infix fun from(value: String): ThemeBrand =
            ThemeBrand.entries.firstOrNull { it.name == value } ?: DEFAULT
    }
}
