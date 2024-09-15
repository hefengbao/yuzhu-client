package com.hefengbao.yuzhu.data.repository

import com.hefengbao.yuzhu.common.network.Result
import com.hefengbao.yuzhu.common.network.SafeApiCall
import com.hefengbao.yuzhu.data.datastore.AppPreference
import com.hefengbao.yuzhu.data.model.AuthToken
import com.hefengbao.yuzhu.data.model.User
import com.hefengbao.yuzhu.data.model.UserData
import com.hefengbao.yuzhu.data.network.Network
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserDataRepositoryImpl @Inject constructor(
    private val appPreference: AppPreference,
    private val network: Network
) : UserDataRepository, SafeApiCall {
    override fun getUserData(): Flow<UserData> = appPreference.appStatus
    override suspend fun setDomain(domain: String) = appPreference.setDomain(domain)
    override suspend fun setUser(user: User) = appPreference.setUser(user)
    override suspend fun setAuthToken(token: AuthToken) = appPreference.setToken(token)
    override suspend fun me(authorization: String?): Result<User> =
        safeApiCall { network.me(authorization) }
}