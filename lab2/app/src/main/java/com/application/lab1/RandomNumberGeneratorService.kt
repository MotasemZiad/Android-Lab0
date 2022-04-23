package com.application.lab1

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.Handler
import android.os.HandlerThread
import android.os.IBinder
import android.util.Log

class RandomNumberGeneratorService : Service() {
    private val HANDLER_THREAD_NAME = "random_number_generator_thread"
    lateinit var handlerThread: HandlerThread
    lateinit var handler: Handler
    var randomNumber: Int = -1
    private val TAG = RandomNumberGeneratorService::class.java.simpleName
    lateinit var manger: MyNotificationManager

    val binder: IBinder = RandomNumberGeneratorServiceBinder()

    inner class RandomNumberGeneratorServiceBinder : Binder() {
        val service: RandomNumberGeneratorService = this@RandomNumberGeneratorService
    }

    override fun onBind(intent: Intent): IBinder {
        return binder
    }

    override fun onCreate() {

        super.onCreate()
        manger = MyNotificationManager(this)
        Log.d(TAG, "service created")
        handlerThread = HandlerThread(HANDLER_THREAD_NAME)
        handlerThread.start()
        handler = Handler(handlerThread.looper)
        handler.post {
            startGeneratingRandomNumber()
        }
        startForeground(
            manger.getNotificationId(),
            manger.getNotification(this)
        )
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_NOT_STICKY
    }

    override fun onDestroy() {
        generateRandomNumber = false
        handler.removeCallbacksAndMessages(null)
        handler.looper.quit()
        handlerThread.quit()
    }

    var generateRandomNumber = false
    private fun startGeneratingRandomNumber() {
        generateRandomNumber = true
        while (generateRandomNumber) {
            Thread.sleep(1000)
            randomNumber = (Math.random() * 100).toInt()
        }
    }
}
