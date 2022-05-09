package com.example.ui.SplashScreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class SplashActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Handler(Looper.getMainLooper()).postDelayed(
            {
                // This method will be executed once the timer is over
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
            },
            1500 // value in milliseconds
        )
    }

}