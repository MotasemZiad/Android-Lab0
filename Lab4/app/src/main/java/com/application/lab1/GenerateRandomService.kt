package com.application.lab1

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.Handler
import android.os.HandlerThread
import android.os.IBinder
import androidx.localbroadcastmanager.content.LocalBroadcastManager

class GenerateRandomService : Service() {
    var number: Int = -1
    lateinit var handlerThread: HandlerThread
    lateinit var handler: Handler


    override fun onBind(p0: Intent?): IBinder? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate() {
        super.onCreate()
        handlerThread = HandlerThread("GENERATE RANDOM")
        handlerThread.start()
        handler = Handler(handlerThread.looper)
        handler.post {
            randomNumber()
        }

    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_NOT_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        isGenerated = false
        handler.removeCallbacksAndMessages(null)
        handler.looper.quit()
        handlerThread.quit()
    }

    var isGenerated = false
    fun randomNumber() {
        isGenerated = true
        while (isGenerated) {
            Thread.sleep(1000)
            number = (Math.random() * 100).toInt()
            val intent = Intent("MyBroadcast")
            intent.putExtra("data", number)
            LocalBroadcastManager.getInstance(this).sendBroadcast(intent)
        }
    }
}
