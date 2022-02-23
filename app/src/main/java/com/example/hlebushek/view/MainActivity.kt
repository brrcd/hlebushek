package com.example.hlebushek.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.hlebushek.R
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.hlebushek.viewmodel.MainActivityViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.android.AndroidInjection
import dagger.android.AndroidInjection.inject
import dagger.android.AndroidInjector
import javax.inject.Inject


class MainActivity : AppCompatActivity(R.layout.activity_main) {

    @Inject
    lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        inject(this)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)!!
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        val navController = navHostFragment.findNavController()
        navView.setupWithNavController(navController)
    }
}