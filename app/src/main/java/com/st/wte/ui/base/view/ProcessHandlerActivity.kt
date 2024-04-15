package com.st.wte.ui.base.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.st.wte.databinding.ActivityProcessHandlerBinding

class ProcessHandlerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProcessHandlerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        manageSplash(splashScreen)
        binding = ActivityProcessHandlerBinding.inflate(layoutInflater).also { setContentView(it.root) }
        binding.restart.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK))
            finish()
        }
    }

    private fun manageSplash(splashScreen: SplashScreen) {
        splashScreen.setOnExitAnimationListener { it.remove() }
    }
}