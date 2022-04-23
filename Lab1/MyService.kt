package com.application.lab1

import android.R
import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.provider.Settings
import android.widget.Toast
import androidx.annotation.Nullable


class MyService : Service() {
    var myPlayer: MediaPlayer? = null
    @Nullable
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        Toast.makeText(this, "Service Created", Toast.LENGTH_LONG).show()
        myPlayer = MediaPlayer.create(this, Settings.System.DEFAULT_ALARM_ALERT_URI )
        myPlayer!!.isLooping = true // Set looping
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Toast.makeText(this, "Service Started", Toast.LENGTH_LONG).show()
        myPlayer!!.start()
        return START_STICKY
    }


    override fun onDestroy() {
//        Toast.makeText(this, "Service Stopped", Toast.LENGTH_LONG).show()
//        myPlayer!!.stop()
    }
}