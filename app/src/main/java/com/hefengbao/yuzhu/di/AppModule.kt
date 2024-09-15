package com.hefengbao.yuzhu.di

import android.content.Context
import com.hefengbao.yuzhu.App
import com.hefengbao.yuzhu.common.storage.AndroidImageDownloader
import com.hefengbao.yuzhu.data.datastore.AppPreference
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApp(): App = App()

    @Provides
    @Singleton
    fun providesNetworkJson(): Json = Json {
        ignoreUnknownKeys = true
        isLenient = true
    }

    @Provides
    @Singleton
    fun providesAppPreference(
        @ApplicationContext context: Context
    ): AppPreference = AppPreference(context)

    @Provides
    @Singleton
    fun providesAndroidImageDownloader(
        @ApplicationContext context: Context,
        @IODispatcher ioDispatcher: CoroutineDispatcher,
        okHttpClient: OkHttpClient,
    ): AndroidImageDownloader = AndroidImageDownloader(context, ioDispatcher, okHttpClient)
}