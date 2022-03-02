package com.example.services.AlarmManager

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.example.services.messaging_receiver_service.ConstantsActions
import com.example.services.messaging_receiver_service.ConstantsActions.ACTION_TAREA_1
import com.example.services.messaging_receiver_service.ConstantsActions.ACTION_TAREA_2
import com.example.services.messaging_receiver_service.ConstantsActions.ACTION_TAREA_3
import timber.log.Timber

class AlarmBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent?) {
        intent?.let { intent ->
            when (intent.action) {
                ACTION_TAREA_3 -> {
                    Toast.makeText(context, "Tarea 3", Toast.LENGTH_LONG).show()
                    Timber.i("Tarea 3")
                }
            }
        }

    }
}