package com.example.services.messaging_receiver_service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.example.services.messaging_receiver_service.ConstantsActions.ACTION_TAREA_1
import com.example.services.messaging_receiver_service.ConstantsActions.ACTION_TAREA_2
import com.example.services.messaging_receiver_service.ConstantsActions.ACTION_TAREA_3

class BroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent?) {
        intent?.let { intent ->
            when (intent.action) {
                ACTION_TAREA_1 -> {
                    tarea1(context)
                    Toast.makeText(context, "Tarea 1", Toast.LENGTH_LONG).show()
                }
                ACTION_TAREA_2 -> {
                    tarea2(context)
                    Toast.makeText(context, "Tarea 2", Toast.LENGTH_LONG).show()
                }
                ACTION_TAREA_3 -> {
                    tarea3(context)
                    Toast.makeText(context, "Tarea 3", Toast.LENGTH_LONG).show()
                }
            }
        }

    }

    private fun tarea1(context: Context) {
        val tarea1Intent = Intent(context, MyService::class.java)
        tarea1Intent.action = ConstantsActions.ACTION_SERVICE
        context.startService(tarea1Intent)
    }

    private fun tarea2(context: Context) {
        TODO("Not yet implemented")
    }

    private fun tarea3(context: Context) {
        TODO("Not yet implemented")
    }
}