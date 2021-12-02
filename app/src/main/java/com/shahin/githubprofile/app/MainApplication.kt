package com.shahin.githubprofile.app

import android.app.Application
import com.shahin.githubprofile.di.ApplicationGraph
import com.shahin.githubprofile.di.DaggerApplicationGraph

class MainApplication : Application() {
    lateinit var applicationGraph: ApplicationGraph
        private set

    override fun onCreate() {
        super.onCreate()
        applicationGraph = DaggerApplicationGraph.builder()
            .application(this)
            .build()
    }

}