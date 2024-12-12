package com.hefengbao.yuzhu.data.repository.auth

import com.hefengbao.yuzhu.common.network.Result
import com.hefengbao.yuzhu.common.network.SafeApiCall
import com.hefengbao.yuzhu.data.model.auth.AuthToken
import com.hefengbao.yuzhu.data.network.Network
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val network: Network
) : AuthRepository, SafeApiCall {
    override suspend fun login(
        email: String,
        password: String,
        userAgent: String
    ): Result<AuthToken> = safeApiCall {
        network.login(email, password, userAgent)
    }
}