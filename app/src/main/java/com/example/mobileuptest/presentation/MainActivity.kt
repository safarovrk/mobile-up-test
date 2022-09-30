package com.example.mobileuptest.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.mobileuptest.R

class MainActivity : AppCompatActivity() {

    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setContentView(R.layout.activity_main)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        savedInstanceState?.getInt("currentDestination")?.let { navController.navigate(it) }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        navController.currentDestination?.id?.let { outState.putInt("currentDestination", it) }
        super.onSaveInstanceState(outState)
    }
}