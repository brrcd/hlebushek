package com.example.hlebushek.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.hlebushek.R
import com.example.hlebushek.view.FragmentMarket.Companion.MARKET_TAG
import com.example.hlebushek.view.FragmentPortfolio.Companion.PORTFOLIO_TAG
import com.example.hlebushek.view.FragmentSettings.Companion.SETTINGS_TAG
import com.google.android.material.navigation.NavigationBarView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavView: NavigationBarView = findViewById(R.id.bottom_navigation_view)
        bottomNavView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.bottom_view_portfolio -> {
                    replaceFragment(FragmentPortfolio(), PORTFOLIO_TAG)
                    true
                }
                R.id.bottom_view_market -> {
                    replaceFragment(FragmentMarket(), MARKET_TAG)
                    true
                }
                R.id.bottom_view_settings -> {
                    replaceFragment(FragmentSettings(), SETTINGS_TAG)
                    true
                }
                else -> false
            }

        }
    }

    private fun addFragment(fragment: Fragment, tag: String) {
        val fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction()
            .add(R.id.activity_main_container, fragment)
            .addToBackStack(tag)
            .commit()
    }

    private fun replaceFragment(fragment: Fragment, tag: String) {
        val fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction()
            .replace(R.id.activity_main_container, fragment)
            .addToBackStack(tag)
            .commit()
    }
}