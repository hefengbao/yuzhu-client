package com.hefengbao.yuzhu.di

import com.hefengbao.yuzhu.data.network.CustomDispatchers
import com.hefengbao.yuzhu.data.network.Dispatcher
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
object DispatchersModule {
    @Provides
    @Dispatcher(CustomDispatchers.IO)
    fun providesIODispatchers(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    @Dispatcher(CustomDispatchers.Default)
    fun providesDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default
}