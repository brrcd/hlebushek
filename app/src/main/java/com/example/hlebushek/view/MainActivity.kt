package com.example.hlebushek.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.hlebushek.R
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.hlebushek.services.TraderService
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)!!
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        val navController = navHostFragment.findNavController()
        navView.setupWithNavController(navController)
        startService(Intent(this, TraderService::class.java))
    }

    override fun onDestroy() {
        super.onDestroy()
        stopService(Intent(this, TraderService::class.java))
    }
}