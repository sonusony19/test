package com.st.wte.application

import android.content.Context
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.st.wte.BuildConfig
import com.st.wte.koin.appModule
import com.st.wte.koin.networkModule
import com.st.wte.koin.viewModels
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class ThisApplication : MultiDexApplication() {

    companion object {
        private lateinit var appContext: Context
        fun getContext() = appContext
    }

    override fun onCreate() {
        super.onCreate()
        appContext = this
        initCrashHandler()
        initKoin()
    }

    private fun initCrashHandler() {
        Thread.setDefaultUncaughtExceptionHandler(ProcessHandler(this))
    }

    private fun initKoin() {
        startKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@ThisApplication)
            modules(appModule, viewModels, networkModule)
        }
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}