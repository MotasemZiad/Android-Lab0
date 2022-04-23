package com.application.lab1


import android.app.ProgressDialog
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import coil.api.load
import coil.transform.CircleCropTransformation
import kotlinx.android.synthetic.main.activity_async_task.*
import java.net.HttpURLConnection
import java.net.URL


class AsyncTaskActivity : AppCompatActivity() {
    lateinit var progressDialog: ProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_async_task)

        btn_start.setOnClickListener {
            val exampleAsyncTask = ExampleAsyncTask()
            exampleAsyncTask.execute("https://media.sproutsocial.com/uploads/2017/02/10x-featured-social-media-image-size.png")
        }

//        btn_start.setOnClickListener {
//            image.load("https://media.sproutsocial.com/uploads/2017/02/10x-featured-social-media-image-size.png") {
//                placeholder(R.mipmap.ic_launcher)
//                transformations(CircleCropTransformation())
//            }
//        }

    }

    inner class ExampleAsyncTask : AsyncTask<String?, String?, Bitmap>() {

        override fun onPreExecute() {
            super.onPreExecute()
            progressDialog = ProgressDialog(this@AsyncTaskActivity)
            progressDialog.setMessage("جاري تحميل الصورة.. الرجاء الإنتظار")
            progressDialog.setCancelable(false)
            progressDialog.show()
        }

        override fun doInBackground(vararg p0: String?): Bitmap {
            val imageUrl = URL(p0[0])
            val connection = imageUrl.openConnection() as HttpURLConnection
            connection.connect()
            val inputStream = connection.inputStream
            val bitmapImage = BitmapFactory.decodeStream(inputStream)
            return bitmapImage
        }

        override fun onPostExecute(result: Bitmap?) {
            super.onPostExecute(result)
            image.setImageBitmap(result)
            progressDialog.dismiss()
        }

    }
}

