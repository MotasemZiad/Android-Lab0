package com.application.lab1

import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

import retrofit2.Call
import retrofit2.Callback

class MainActivity : AppCompatActivity(), SocialAdapter.onClick {
    lateinit var progressDialog: ProgressDialog
    lateinit var socialAdapter: SocialAdapter
    lateinit var socialsList: ArrayList<Social>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        progressDialog = ProgressDialog(this)
        progressDialog.setMessage("جاري التحميل")
        progressDialog.setCancelable(false)
        socialsList = ArrayList()
        getSocials()
    }

    fun getSocials() {
        val service: ApiInterface =
            ApiClient.getRetrofitInstance()!!.create(ApiInterface::class.java)

        val call = service.getSocials()
        call.enqueue(object :Callback<Social>{
            override fun onFailure(call: Call<Social>, t: Throwable) {

            }

            override fun onResponse(call: Call<Social>, response: retrofit2.Response<Social>) {
                if (response.isSuccessful){
                    val social = response.body()
                    intitAdapter(social)
                }
            }

        })

//        val stringRequest =
//            StringRequest(
//                Request.Method.GET,
//                URLs.GET_SOCIALS,
//                Response.Listener { response ->
//                    progressDialog.dismiss()
//                    Toast.makeText(this, response, Toast.LENGTH_SHORT).show()
//                    val jsonObject = JSONObject(response)
//                    val array = jsonObject.getJSONArray("records")
//                    for (i in 0 until array.length()) {
//                        socialsList.add(
//                            Social(
//                                array.getJSONObject(i).getString("social_id"),
//                                array.getJSONObject(i).getString("title"),
//                                array.getJSONObject(i).getString("url"),
//                                array.getJSONObject(i).getString("icon")
//                            )
//                        )
//                    }
//                    socialAdapter = SocialAdapter(this,socialsList,this)
//                    recycle.adapter = socialAdapter
//                    recycle.layoutManager = LinearLayoutManager(this)
////                tv_email.text = jsonObject.getString("email")
////                tv_phone.text = jsonObject.getString("phone")
//
//                },
//                Response.ErrorListener { error ->
//
//                })
//
//        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest)
    }

    private fun intitAdapter(social: Social?) {
        socialAdapter = SocialAdapter(this, social!!.list, this)
        recycle.adapter = socialAdapter
        recycle.layoutManager = LinearLayoutManager(this)
    }

    override fun onClickItem(position: Int) {
//        var intent = Intent(Intent.ACTION_VIEW, Uri.parse(socialsList[position].url))
//        startActivity(intent)
    }


}








