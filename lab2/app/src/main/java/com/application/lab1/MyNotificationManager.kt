package com.application.lab1

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat

class MyNotificationManager(var mCtx: Context?) {
    private val ID_SMALL_NOTIFICATION = 235
    private val NOTIFICATION_ID = 1094

    fun showSmallNotification(
        id: Int,
        title: String?,
        message: String?,
        intent: Intent?
    ) {
        val resultPendingIntent = PendingIntent.getActivity(
            mCtx,
            ID_SMALL_NOTIFICATION,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        val channelId = "channel_id"
        val mBuilder = NotificationCompat.Builder(mCtx!!, channelId)
        val notification: Notification
        notification = mBuilder
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentIntent(resultPendingIntent)
            .setContentTitle(title)
            .setContentText(message)
            .setAutoCancel(false)
            .setPriority(NotificationManager.IMPORTANCE_HIGH)
            .build()


        val notificationManager =
            (mCtx!!.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Channel human readable title",
                NotificationManager.IMPORTANCE_HIGH
            )
            channel.setShowBadge(false)
            channel.description = message
            channel.enableLights(false)
            channel.enableVibration(false)
            notificationManager.createNotificationChannel(channel)
        }


        notificationManager.notify(id, notification)
    }

    fun getNotificationId(): Int {
        return NOTIFICATION_ID
    }

    fun getNotification(context: Context): Notification? {
        val channelId = "cahnnel_id2"

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val manager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            val name: CharSequence = "Foreground Service"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(channelId, name, importance)
            channel.setShowBadge(false)
            channel.description = "Foreground Service is running"
            channel.enableLights(false)
            channel.enableVibration(false)
            channel.setSound(null, null)

            assert(manager != null)
            manager.createNotificationChannel(channel)

            val pendingIntent = PendingIntent.getActivity(
                context, 0, Intent(
                    context,
                    MainActivity::class.java
                ), 0
            )

            val builder =
                Notification.Builder(context, channelId)
            builder.setContentTitle("Foreground Service")
                .setCategory(Notification.CATEGORY_SERVICE)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pendingIntent)
                .setOngoing(true)
                .setAutoCancel(true)
            val notification = builder.build()
            return notification

        } else {
            val pendingIntent = PendingIntent.getActivity(
                context, 0, Intent(
                    context,
                    MainActivity::class.java
                ), 0
            )
            val builder = NotificationCompat.Builder(context)
                .setCategory(Notification.CATEGORY_SERVICE)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pendingIntent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true)
            return builder.build()
        }

    }
}