package com.hefengbao.yuzhu.di

import com.hefengbao.yuzhu.data.network.Network
import com.hefengbao.yuzhu.data.network.retrofit.NetworkImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface NetworkDatasourceModule {
    @Binds
    fun bindsNetwork(
        networkImpl: NetworkImpl
    ): Network
}