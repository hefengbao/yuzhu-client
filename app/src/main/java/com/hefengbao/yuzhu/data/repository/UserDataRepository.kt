package com.hefengbao.yuzhu.data.repository

import com.hefengbao.yuzhu.common.network.Result
import com.hefengbao.yuzhu.data.model.AuthToken
import com.hefengbao.yuzhu.data.model.User
import com.hefengbao.yuzhu.data.model.UserData
import kotlinx.coroutines.flow.Flow

interface UserDataRepository {
    fun getUserData(): Flow<UserData>
    suspend fun setDomain(domain: String)
    suspend fun setUser(user: User)
    suspend fun setAuthToken(token: AuthToken)
    suspend fun me(authorization: String?): Result<User>
}