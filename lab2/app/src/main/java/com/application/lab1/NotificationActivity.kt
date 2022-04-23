package com.application.lab1

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_notification.*


class NotificationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)

        button.setOnClickListener {
            val intent = Intent(this, NotificationViewActivity::class.java)
            intent.putExtra("message", "l;dmcvdsklvmdslmv")
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

            val manager = MyNotificationManager(this)
            manager.showSmallNotification(1, "test", "hello", intent)
        }


    }


}
