package com.application.lab1

import android.os.*
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    val TAG = "MainActivity"
    lateinit var myThread: MyThread
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        myThread = MyThread()

        btn_start.setOnClickListener {
            myThread.start()
        }

        btn_taska.setOnClickListener {
            val msg: Message = Message.obtain()
            msg.what = MyHandler.TASK_A
            myThread.handler.sendMessage(msg)
//            myThread.handler.post {
//                for (i in 0..5) {
//                    Log.e(TAG, "run $i")
//                    Thread.sleep(1000)
//                }
//            }

        }
        btn_taskb.setOnClickListener {
            val msg: Message = Message.obtain()
            msg.what = MyHandler.TASK_B
            myThread.handler.sendMessage(msg)
        }

        btn_end.setOnClickListener {
            myThread.looper!!.quit()
        }

    }
}








