package com.hefengbao.yuzhu.di

import com.hefengbao.yuzhu.common.util.ConnectivityManagerNetworkMonitor
import com.hefengbao.yuzhu.common.util.NetworkMonitor
import com.hefengbao.yuzhu.data.repository.ArticleRepository
import com.hefengbao.yuzhu.data.repository.ArticleRepositoryImpl
import com.hefengbao.yuzhu.data.repository.AuthRepository
import com.hefengbao.yuzhu.data.repository.AuthRepositoryImpl
import com.hefengbao.yuzhu.data.repository.SettingsRepository
import com.hefengbao.yuzhu.data.repository.SettingsRepositoryImpl
import com.hefengbao.yuzhu.data.repository.TagRepository
import com.hefengbao.yuzhu.data.repository.TagRepositoryImpl
import com.hefengbao.yuzhu.data.repository.TweetRepository
import com.hefengbao.yuzhu.data.repository.TweetRepositoryImpl
import com.hefengbao.yuzhu.data.repository.UserDataRepository
import com.hefengbao.yuzhu.data.repository.UserDataRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindsNetworkMonitor(
        networkMonitor: ConnectivityManagerNetworkMonitor
    ): NetworkMonitor

    @Binds
    fun bindsArticleRepository(
        repository: ArticleRepositoryImpl
    ): ArticleRepository

    @Binds
    fun bindsAuthRepository(
        repository: AuthRepositoryImpl
    ): AuthRepository

    @Binds
    fun bindsSettingsRepository(
        repository: SettingsRepositoryImpl
    ): SettingsRepository

    @Binds
    fun bindsTagRepository(
        repository: TagRepositoryImpl
    ): TagRepository

    @Binds
    fun bindsTweetRepository(
        repository: TweetRepositoryImpl
    ): TweetRepository

    @Binds
    fun bindsUserDataRepository(
        repository: UserDataRepositoryImpl
    ): UserDataRepository
}