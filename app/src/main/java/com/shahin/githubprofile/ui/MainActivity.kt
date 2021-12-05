package com.shahin.githubprofile.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.shahin.githubprofile.R
import com.shahin.githubprofile.app.MainApplication
import com.shahin.githubprofile.di.MainActivitySubComponent

class MainActivity : AppCompatActivity() {

    lateinit var mainActivitySubComponent: MainActivitySubComponent
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {

        mainActivitySubComponent =
            (applicationContext as MainApplication).applicationGraph.mainComponent().create()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initNavController()

    }

    private fun initNavController() {
        val navView: BottomNavigationView = findViewById(R.id.bottom_nav_view)
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        NavigationUI.setupWithNavController(navView, navController)
    }

}