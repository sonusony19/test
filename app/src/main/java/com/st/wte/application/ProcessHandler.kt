package com.st.wte.application

import android.content.Context
import android.content.Intent
import android.util.Log
import com.st.wte.BuildConfig
import com.st.wte.ui.base.view.ProcessHandlerActivity
import kotlin.system.exitProcess


class ProcessHandler(private val context: Context) : Thread.UncaughtExceptionHandler {
    override fun uncaughtException(t: Thread, e: Throwable) {
        if (BuildConfig.DEBUG) {
            Log.e(this.javaClass.simpleName, "uncaughtException: ${e.stackTraceToString()}")
        }
        val handlerIntent = Intent(context.applicationContext, ProcessHandlerActivity::class.java)
        handlerIntent.flags = (Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(handlerIntent)
        android.os.Process.killProcess(android.os.Process.myPid())
        exitProcess(0)
    }
}