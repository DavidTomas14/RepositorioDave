package com.example.services

import android.app.Application
import timber.log.Timber

class ServicesApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())
    }
}
