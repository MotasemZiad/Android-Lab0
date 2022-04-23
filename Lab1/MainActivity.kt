package com.application.lab1

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buttonStart.setOnClickListener(this);
        buttonStop.setOnClickListener ( this)


    }

    override fun onClick(p0: View?) {
        when(p0!!.id){
            R.id.buttonStart -> startService(Intent(this, MyService::class.java))
            R.id.buttonStop -> stopService(Intent(this, MyService::class.java))
        }
    }


}
