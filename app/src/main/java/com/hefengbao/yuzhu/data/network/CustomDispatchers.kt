package com.hefengbao.yuzhu.data.network

import javax.inject.Qualifier
import kotlin.annotation.AnnotationRetention.RUNTIME

@Qualifier
@Retention(RUNTIME)
annotation class Dispatcher(val dispatchers: CustomDispatchers)

enum class CustomDispatchers {
    Default,
    IO,
}
