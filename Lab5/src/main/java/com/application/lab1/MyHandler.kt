package com.application.lab1

import android.os.Handler
import android.os.Message
import android.util.Log

class MyHandler :Handler() {
    val TAG = "MyHandler"

    override fun handleMessage(msg: Message) {
        super.handleMessage(msg)
        when(msg.what){
            TASK_A ->Log.e(TAG,"This is From TaskA")
            TASK_B ->Log.e(TAG,"This is From TaskB")
        }
    }

    companion object{
        val TASK_A = 1
        val TASK_B = 2
    }
}