package com.example.services.Service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import timber.log.Timber


class MyService : Service() {

    init {
        Timber.d("Service is running...")
    }
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val dataString = intent?.getStringExtra("EXTRA_DATA")
        dataString?.let {
            Timber.d(dataString)
        }
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.d("Service is being killed")
    }
}