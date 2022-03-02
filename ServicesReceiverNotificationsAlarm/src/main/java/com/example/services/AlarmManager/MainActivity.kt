package com.example.services.AlarmManager

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.services.databinding.ActivityMainBinding
import com.example.services.messaging_receiver_service.ConstantsActions
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    companion object{
        const val UPDATE_TIME = (1000 * 30).toLong()
    }
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setAlarm()
    }

    @SuppressLint("ShortAlarm")
    private fun setAlarm() {
        val am = applicationContext.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val commandIntent = Intent(applicationContext, AlarmBroadcastReceiver::class.java )
        commandIntent.action = ConstantsActions.ACTION_TAREA_3
        val pi = PendingIntent.getBroadcast(
            applicationContext, 14, commandIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        am.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + UPDATE_TIME, UPDATE_TIME, pi)
    }

}