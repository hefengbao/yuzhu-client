package com.hefengbao.yuzhu

import com.hefengbao.yuzhu.common.ext.asBearerToken

object AppStatus {
    var domain: String? = null
    var accessToken: String? = null

    fun domain(domain: String) {
        this.domain = domain
    }

    fun accessToken(token: String) {
        this.accessToken = token.asBearerToken()
    }
}