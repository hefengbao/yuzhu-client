package com.hefengbao.yuzhu.data.model

import com.hefengbao.yuzhu.common.ext.toSeconds
import com.hefengbao.yuzhu.data.model.theme.DarkThemeConfig
import com.hefengbao.yuzhu.data.model.theme.ThemeBrand
import java.util.Date

data class UserData(
    val domain: String?,
    val user: User,
    val accessToken: String?,
    val expiresAt: String?,
    val themeBrand: ThemeBrand,
    val darkThemeConfig: DarkThemeConfig,
    val useDynamicColor: Boolean,
) {
    val shouldLogin = accessToken == null ||
            expiresAt == null ||
            expiresAt.toSeconds() <= Date().time
    val shouldSetDomain = domain.isNullOrEmpty()
}