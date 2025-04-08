package com.hefengbao.yuzhu.di

import com.hefengbao.yuzhu.common.util.ConnectivityManagerNetworkMonitor
import com.hefengbao.yuzhu.common.util.NetworkMonitor
import com.hefengbao.yuzhu.data.repository.auth.AuthRepository
import com.hefengbao.yuzhu.data.repository.auth.AuthRepositoryImpl
import com.hefengbao.yuzhu.data.repository.finance.AccountRepository as FinanceAccountRepository
import com.hefengbao.yuzhu.data.repository.finance.AccountRepositoryImpl as FinanceAccountRepositoryImpl
import com.hefengbao.yuzhu.data.repository.finance.CategoryRepository as FinanceCategoryRepository
import com.hefengbao.yuzhu.data.repository.finance.CategoryRepositoryImpl as FinanceCategoryRepositoryImpl
import com.hefengbao.yuzhu.data.repository.finance.GroupRepository as FinanceGroupRepository
import com.hefengbao.yuzhu.data.repository.finance.GroupRepositoryImpl as FinanceGroupRepositoryImpl
import com.hefengbao.yuzhu.data.repository.finance.TransactionRepository as FinanceTransactionRepository
import com.hefengbao.yuzhu.data.repository.finance.TransactionRepositoryImpl as FinanceTransactionRepositoryImpl
import com.hefengbao.yuzhu.data.repository.post.ArticleRepository
import com.hefengbao.yuzhu.data.repository.post.ArticleRepositoryImpl
import com.hefengbao.yuzhu.data.repository.post.TagRepository
import com.hefengbao.yuzhu.data.repository.post.TagRepositoryImpl
import com.hefengbao.yuzhu.data.repository.post.TweetRepository
import com.hefengbao.yuzhu.data.repository.post.TweetRepositoryImpl
import com.hefengbao.yuzhu.data.repository.settings.SettingsRepository
import com.hefengbao.yuzhu.data.repository.settings.SettingsRepositoryImpl
import com.hefengbao.yuzhu.data.repository.user.UserDataRepository
import com.hefengbao.yuzhu.data.repository.user.UserDataRepositoryImpl
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

    @Binds
    fun bindsFinanceAccountRepository(
        repository: FinanceAccountRepositoryImpl
    ): FinanceAccountRepository

    @Binds
    fun bindsFinanceCategoryRepository(
        repository: FinanceCategoryRepositoryImpl
    ): FinanceCategoryRepository

    @Binds
    fun bindsFinanceGroupRepository(
        repository: FinanceGroupRepositoryImpl
    ): FinanceGroupRepository

    @Binds
    fun bindsFinanceTransactionRepository(
        repository: FinanceTransactionRepositoryImpl
    ): FinanceTransactionRepository
}