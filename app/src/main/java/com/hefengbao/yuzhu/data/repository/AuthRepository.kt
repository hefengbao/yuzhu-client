package com.hefengbao.yuzhu.data.repository

import com.hefengbao.yuzhu.common.network.Result
import com.hefengbao.yuzhu.data.model.AuthToken

interface AuthRepository {
    suspend fun login(email: String, password: String, userAgent: String): Result<AuthToken>
}