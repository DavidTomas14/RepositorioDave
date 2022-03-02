package com.example.services.messaging_receiver_service

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.*
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.services.R
import timber.log.Timber


class MyService : Service() {


    private var serviceLooper: Looper? = null
    private var serviceHandler: ServiceHandler? = null

    // Handler that receives messages from the thread
    private inner class ServiceHandler(looper: Looper) : Handler(looper) {

        override fun handleMessage(msg: Message) {

            when (msg.data["ACTION_KEY"]) {
                ConstantsActions.ACTION_SERVICE -> {
                    createNotificationChannel()
                    createAndStartNotification()
                }
                ConstantsActions.ACTION_NOTIFICATION -> {
                    Timber.d("Action Notification running")
                    try {
                        Thread.sleep(5000)
                    } catch (e: InterruptedException) {
                        // Restore interrupt status.
                        Thread.currentThread().interrupt()
                    }
                    stopForeground(true)
                    stopSelf()

                }
            }
            // Stop the service using the startId, so that we don't stop
            // the service in the middle of handling another job
            stopSelf(msg.arg1)
        }
    }

    override fun onCreate() {
        // Start up the thread running the service.  Note that we create a
        // separate thread because the service normally runs in the process's
        // main thread, which we don't want to block.  We also make it
        // background priority so CPU-intensive work will not disrupt our UI.

        //compositeDisposable = CompositeDisposable()-> Creamos el composite como contenedor de UseCases

        HandlerThread("ServiceStartArguments", Process.THREAD_PRIORITY_BACKGROUND).apply {
            start()

            // Get the HandlerThread's Looper and use it for our Handler
            serviceLooper = looper
            serviceHandler = ServiceHandler(looper)
        }
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        Toast.makeText(this, "service starting", Toast.LENGTH_SHORT).show()

        var actionBundle = Bundle()
        actionBundle.putString("ACTION_KEY", intent.action)
        // For each start request, send a message to start a job and deliver the
        // start ID so we know which request we're stopping when we finish the job
        serviceHandler?.obtainMessage()?.also { msg ->
            msg.data = actionBundle
            serviceHandler?.sendMessage(msg)
        }

        // If we get killed, after returning from here, restart
        return START_STICKY
    }

    override fun onBind(intent: Intent): IBinder? {
        // We don't provide binding, so return null
        return null
    }

    override fun onDestroy() {
        Toast.makeText(this, "service done", Toast.LENGTH_SHORT).show()
    }

    private fun createAndStartNotification() {
        val notificationIntent = Intent(this, MyService::class.java).let {
            it.action = ConstantsActions.ACTION_NOTIFICATION
            PendingIntent.getService(
                applicationContext, 1, it,
                PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_IMMUTABLE
            )
        }


        val notification = NotificationCompat.Builder(this, "CHANNEL_ID")
            .setContentTitle("Service is running...")
            .setContentText("This is the content text")
            .setSmallIcon(R.drawable.ic_baseline_circle_notifications_24)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(notificationIntent)
            .setAutoCancel(true)
            .build()

        startForeground(1, notification)

    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // iMPORTANCE para ponerle sonidos a las notificaciones
            val channel = NotificationChannel("CHANNEL_ID", "CHANNEL_NAME", NotificationManager.IMPORTANCE_DEFAULT)
                .apply {
                    lightColor = Color.GREEN
                    enableLights(true)
                }

            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }
    }
}