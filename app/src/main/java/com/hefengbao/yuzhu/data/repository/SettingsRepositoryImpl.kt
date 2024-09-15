package com.hefengbao.yuzhu.data.repository

import com.hefengbao.yuzhu.data.datastore.AppPreference
import com.hefengbao.yuzhu.data.model.UserData
import com.hefengbao.yuzhu.data.model.theme.DarkThemeConfig
import com.hefengbao.yuzhu.data.model.theme.ThemeBrand
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SettingsRepositoryImpl @Inject constructor(
    private val preference: AppPreference
) : SettingsRepository {
    override val appStatus: Flow<UserData>
        get() = preference.appStatus

    override suspend fun setThemeBrand(themeBrand: ThemeBrand) =
        preference.setThemeBrand(themeBrand)

    override suspend fun setDarkThemeConfig(darkThemeConfig: DarkThemeConfig) =
        preference.setDarkThemeConfig(darkThemeConfig)

    override suspend fun setDynamicColorPreference(useDynamicColor: Boolean) =
        preference.setUseDynamicColor(useDynamicColor)
}