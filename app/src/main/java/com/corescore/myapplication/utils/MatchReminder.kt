package com.corescore.myapplication.utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import corescore.myapplication.R

class MatchReminder: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val heading=intent?.getStringExtra("heading")
        createNotificationChannel(context,"0099")

        var builder = NotificationCompat.Builder(context!!, "0099")
            .setSmallIcon(R.drawable.ic_goal)
            .setContentTitle(heading)
            .setContentText("Much longer text that cannot fit one line...")
            .setStyle(NotificationCompat.BigTextStyle()
                .bigText("Much longer text that cannot fit one line..."))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(context)) {
            // notificationId is a unique int for each notification that you must define
            notify(66, builder.build())
        }
        Toast.makeText(context,"Done!", Toast.LENGTH_LONG).show()

    }
    private fun createNotificationChannel(context: Context?, CHANNEL_ID: String) {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = context?.getString(R.string.channel_name)
            val descriptionText = context?.getString(R.string.channel_name)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    }