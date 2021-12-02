package com.shahin.githubprofile.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.shahin.githubprofile.R
import com.shahin.githubprofile.app.MainApplication
import com.shahin.githubprofile.databinding.ActivityMainBinding
import com.shahin.githubprofile.di.MainActivitySubComponent

class MainActivity : AppCompatActivity() {

    lateinit var mainActivitySubComponent: MainActivitySubComponent

    override fun onCreate(savedInstanceState: Bundle?) {

        mainActivitySubComponent =
            (applicationContext as MainApplication).applicationGraph.mainComponent().create()

        super.onCreate(savedInstanceState)

        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

    }

}