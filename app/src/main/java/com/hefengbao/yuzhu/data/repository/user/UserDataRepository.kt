package com.hefengbao.yuzhu.data.repository.user

import com.hefengbao.yuzhu.common.network.Result
import com.hefengbao.yuzhu.data.model.auth.AuthToken
import com.hefengbao.yuzhu.data.model.user.User
import com.hefengbao.yuzhu.data.model.user.UserData
import kotlinx.coroutines.flow.Flow

interface UserDataRepository {
    fun getUserData(): Flow<UserData>
    suspend fun setDomain(domain: String)
    suspend fun setUser(user: User)
    suspend fun setAuthToken(token: AuthToken)
    suspend fun me(authorization: String?): Result<User>
}