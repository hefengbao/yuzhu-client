package com.hefengbao.yuzhu.data.repository.settings

import com.hefengbao.yuzhu.data.model.theme.DarkThemeConfig
import com.hefengbao.yuzhu.data.model.theme.ThemeBrand
import com.hefengbao.yuzhu.data.model.user.UserData
import kotlinx.coroutines.flow.Flow

interface SettingsRepository {
    val appStatus: Flow<UserData>

    /**
     * Sets the desired theme brand.
     */
    suspend fun setThemeBrand(themeBrand: ThemeBrand)

    /**
     * Sets the desired dark theme config.
     */
    suspend fun setDarkThemeConfig(darkThemeConfig: DarkThemeConfig)

    /**
     * Sets the preferred dynamic color config.
     */
    suspend fun setDynamicColorPreference(useDynamicColor: Boolean)
}