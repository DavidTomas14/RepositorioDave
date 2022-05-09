package com.example.navigation

import android.app.Application
import com.example.ui.DI.applicationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class NavigationApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            // Android context
            androidContext(this@NavigationApplication)
            // modules
            modules(
                listOf(
                    applicationModule
                )
            )
        }
        Timber.plant(Timber.DebugTree())
    }
}
