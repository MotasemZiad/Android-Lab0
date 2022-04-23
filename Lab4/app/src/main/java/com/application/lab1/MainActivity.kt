package com.application.lab1

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    //    val receiver = object : BroadcastReceiver() {
//        override fun onReceive(p0: Context?, intent: Intent?) {
//            when (intent!!.action) {
//                "MyBroadcast" -> {
//                    val data = intent.getStringExtra("data")
//                    Toast.makeText(
//                        this@MainActivity,
//                        data,
//                        Toast.LENGTH_SHORT
//                    ).show()
//                }
//                else -> Toast.makeText(
//                    this@MainActivity,
//                    "No action found",
//                    Toast.LENGTH_SHORT
//                ).show()
//            }
//        }
//
//    }
    var snackbar: Snackbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //        registerReceiver(
//            ConnectivityReceiver(),
//            IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
//        )

        snackbar =
            Snackbar.make(
                findViewById(R.id.root_view),
                "Network disconnected",
                Snackbar.LENGTH_LONG
            )
        snackbar!!.duration = BaseTransientBottomBar.LENGTH_INDEFINITE

//    override fun onNetworkConnectionChanged(isConnected: Boolean) {
//        showNetworkMessage(isConnected)
//    }

//    override fun onResume() {
//        super.onResume()
//        ConnectivityReceiver.connectivityReceiverListener = this
//    }
//    private fun showNetworkMessage(isConnected: Boolean) {
//        if (!connected) {
//            snackbar!!.show()
//        } else {
//            snackbar!!.dismiss()
//        }
//
//    }
//        btn_start.setOnClickListener {
//            startService(Intent(this, GenerateRandomService::class.java))
//        }

    }

//    override fun onResume() {
//        super.onResume()
//        LocalBroadcastManager.getInstance(this)
//            .registerReceiver(receiver, IntentFilter("MyBroadcast"))
//    }
//
//    override fun onPause() {
//        super.onPause()
//        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver)
//    }

//    fun startAlarm() {
//        val num = ed_num.text.toString().toInt()
//        val intent = Intent(this, MyBroadcastReceiver::class.java)
//        val pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0)
//        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
//        alarmManager.set(
//            AlarmManager.RTC_WAKEUP,
//            System.currentTimeMillis() + (num * 1000),
//            pendingIntent
//        )
//        Toast.makeText(this, "alarm is set in $num second", Toast.LENGTH_SHORT).show()
//    }


}







