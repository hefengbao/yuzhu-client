package com.hefengbao.yuzhu.data.model.theme

enum class DarkThemeConfig {
    FOLLOW_SYSTEM,
    LIGHT,
    DARK;

    companion object {
        infix fun from(value: String): DarkThemeConfig =
            DarkThemeConfig.entries.firstOrNull { it.name == value } ?: FOLLOW_SYSTEM
    }
}
