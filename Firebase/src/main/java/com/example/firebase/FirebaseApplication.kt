package com.example.firebase

import android.app.Application

class FirebaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        TimberLogImplementation.init()
    }
}
