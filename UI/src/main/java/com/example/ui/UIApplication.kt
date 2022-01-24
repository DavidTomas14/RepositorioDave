package com.example.ui

import android.app.Application
import com.example.ui.DI.applicationModule
import com.example.ui.DI.spinnerModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class UIApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // start Koin!
        startKoin {
            // Android context
            androidContext(this@UIApplication)
            // modules
            modules(
                listOf(
                    applicationModule, spinnerModule
                )
            ).allowOverride(true)
        }

        Timber.plant(Timber.DebugTree())
    }
}
