package com.hefengbao.yuzhu

import android.app.Application
import com.tencent.bugly.crashreport.CrashReport
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()

        CrashReport.initCrashReport(applicationContext, BuildConfig.BUGLY, BuildConfig.DEBUG)
    }
}