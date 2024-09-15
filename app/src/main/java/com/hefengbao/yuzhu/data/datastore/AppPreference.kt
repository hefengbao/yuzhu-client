package com.hefengbao.yuzhu.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.hefengbao.yuzhu.common.Constant
import com.hefengbao.yuzhu.data.model.AuthToken
import com.hefengbao.yuzhu.data.model.User
import com.hefengbao.yuzhu.data.model.UserData
import com.hefengbao.yuzhu.data.model.theme.DarkThemeConfig
import com.hefengbao.yuzhu.data.model.theme.ThemeBrand
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.app: DataStore<Preferences> by preferencesDataStore(name = Constant.DATASTORE_NAME)

private suspend fun setLong(context: Context, key: Preferences.Key<Long>, value: Long) {
    context.app.edit { it[key] = value }
}

private suspend fun setInt(context: Context, key: Preferences.Key<Int>, value: Int) {
    context.app.edit { it[key] = value }
}

private suspend fun setString(context: Context, key: Preferences.Key<String>, value: String) {
    context.app.edit { it[key] = value }
}

private suspend fun setBoolean(context: Context, key: Preferences.Key<Boolean>, value: Boolean) {
    context.app.edit { it[key] = value }
}

class AppPreference(
    private val context: Context
) {
    val appStatus: Flow<UserData> = context.app.data.map {
        UserData(
            domain = it[PREF_DOMAIN],
            user = User(
                id = it[PREF_USER_ID] ?: 0,
                name = it[PREF_USER_NAME],
                avatar = it[PREF_USER_AVATAR],
                bio = it[PREF_USER_BIO],
                role = it[PREF_USER_ROLE] ?: ""
            ),
            accessToken = it[PREF_AUTH_ACCESS_TOKEN],
            expiresAt = it[PREF_AUTH_EXPIRES_AT],
            themeBrand = ThemeBrand.from(it[PREF_SETTINGS_THEME_BRAND] ?: ThemeBrand.DEFAULT.name),
            darkThemeConfig = DarkThemeConfig.from(
                it[PREF_SETTINGS_DARK_THEME_CONFIG] ?: DarkThemeConfig.FOLLOW_SYSTEM.name
            ),
            useDynamicColor = it[PREF_SETTINGS_USE_DYNAMIC_COLOR] ?: false,
        )
    }

    suspend fun setDomain(domain: String) =
        setString(context, PREF_DOMAIN, domain)

    suspend fun setThemeBrand(brand: ThemeBrand) =
        setString(context, PREF_SETTINGS_THEME_BRAND, brand.name)

    suspend fun setDarkThemeConfig(config: DarkThemeConfig) =
        setString(context, PREF_SETTINGS_DARK_THEME_CONFIG, config.name)

    suspend fun setUseDynamicColor(useDynamicColor: Boolean) =
        setBoolean(context, PREF_SETTINGS_USE_DYNAMIC_COLOR, useDynamicColor)

    suspend fun setUser(user: User) {
        setInt(context, PREF_USER_ID, user.id)
        user.name?.let {
            setString(context, PREF_USER_NAME, it)
        }
        user.avatar?.let {
            setString(context, PREF_USER_AVATAR, it)
        }
        user.bio?.let {
            setString(context, PREF_USER_BIO, it)
        }
        setString(context, PREF_USER_ROLE, user.role)
    }

    suspend fun setToken(token: AuthToken) {
        setString(context, PREF_AUTH_ACCESS_TOKEN, token.accessToken)
        setString(context, PREF_AUTH_EXPIRES_AT, token.expiresAt)
    }

    companion object {
        private val PREF_DOMAIN = stringPreferencesKey("settings_domain")
        private val PREF_SETTINGS_THEME_BRAND = stringPreferencesKey("settings_theme_brand")
        private val PREF_SETTINGS_DARK_THEME_CONFIG =
            stringPreferencesKey("settings_dark_theme_config")
        private val PREF_SETTINGS_USE_DYNAMIC_COLOR =
            booleanPreferencesKey("settings_use_dynamic_color")
        private val PREF_AUTH_ACCESS_TOKEN = stringPreferencesKey("auth_access_token")
        private val PREF_AUTH_EXPIRES_AT = stringPreferencesKey("auth_expires_at")
        private val PREF_USER_ID = intPreferencesKey("user_id")
        private val PREF_USER_NAME = stringPreferencesKey("user_name")
        private val PREF_USER_AVATAR = stringPreferencesKey("user_avatar")
        private val PREF_USER_BIO = stringPreferencesKey("user_bio")
        private val PREF_USER_ROLE = stringPreferencesKey("user_role")
    }
}