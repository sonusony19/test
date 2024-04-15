package com.st.wte.ui.base.view

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.st.wte.R
import com.st.wte.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        manageStartAndWindow()
        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }
        manageOverflowElements()
        init()
    }

    private fun manageStartAndWindow() {
        enableEdgeToEdge()
        installSplashScreen()
    }

    private fun manageOverflowElements() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun init() {
        navController = (supportFragmentManager.findFragmentById(R.id.main) as NavHostFragment).navController
    }
}