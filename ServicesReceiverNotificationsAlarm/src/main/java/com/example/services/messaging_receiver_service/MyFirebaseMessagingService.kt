package com.example.services.messaging_receiver_service

import android.app.PendingIntent
import android.content.Intent
import com.example.services.messaging_receiver_service.ConstantsActions.ACTION_TAREA_1
import com.example.services.messaging_receiver_service.ConstantsActions.ACTION_TAREA_2
import com.example.services.messaging_receiver_service.ConstantsActions.ACTION_TAREA_3
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import timber.log.Timber

class MyFirebaseMessagingService: FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {

        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Timber.d("From: ${remoteMessage.from}")

        val commandId = remoteMessage.data["commandId"]
        val extra = remoteMessage.data["extra"]

        commandId?.let { sendCommandIntent(commandId.toInt(), extra.toString()) }

    }

    private fun sendCommandIntent(commandId: Int, extra: String) {
        val commandIntent = Intent(applicationContext, BroadcastReceiver::class.java)

        commandIntent.action = when(commandId){
            1 -> ACTION_TAREA_1
            2 -> ACTION_TAREA_2
            3 -> ACTION_TAREA_3
            else -> ""
        }

        PendingIntent.getBroadcast(
            applicationContext, 0,
            commandIntent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        ).send()


    }

    override fun onNewToken(token: String) {
        Timber.d("Firebase Device Token : $token")
        super.onNewToken(token)
    }
 }