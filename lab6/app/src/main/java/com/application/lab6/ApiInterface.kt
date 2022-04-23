package com.application.lab1

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.GET

interface ApiInterface {

    @GET("socials_api")
    fun getSocials():Call<Social>
}