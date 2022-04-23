package com.application.lab1

import android.os.Handler
import android.os.Looper
import android.util.Log

class MyThread :Thread() {
    val TAG = "MyThread"
    lateinit var handler: MyHandler
    var looper:Looper? = null
    override fun run() {
        super.run()
        Looper.prepare()
        looper = Looper.myLooper()
        handler = MyHandler()
        Looper.loop()


        Log.e(TAG,"Thread is Finished")
    }
}