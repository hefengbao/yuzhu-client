package com.hefengbao.yuzhu.data.repository.auth

import com.hefengbao.yuzhu.common.network.Result
import com.hefengbao.yuzhu.data.model.auth.AuthToken

interface AuthRepository {
    suspend fun login(email: String, password: String, userAgent: String): Result<AuthToken>
}