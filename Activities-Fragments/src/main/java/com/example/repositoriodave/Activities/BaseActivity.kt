package com.example.repositoriodave.Activities

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * Base [AppCompatActivity] class for every Activity in this application.
 */
abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Light navigation bar is only supported on api 27+ devices
        if (Build.VERSION.SDK_INT < 27) {
            window?.navigationBarColor = Color.BLACK
        }
    }

}
