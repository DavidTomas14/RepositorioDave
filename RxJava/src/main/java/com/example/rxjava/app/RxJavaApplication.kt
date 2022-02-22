package com.example.rxjava.app

import android.app.Application
import com.example.rxjava.app.di.applicationModule
import com.example.rxjava.app.di.userModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class RxJavaApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // start Koin!
        startKoin {
            // Android context
            androidContext(this@RxJavaApplication)
            // modules
            modules(
                listOf(
                    applicationModule, userModule
                )
            ).allowOverride(true)
        }

        Timber.plant(Timber.DebugTree())
    }
}
