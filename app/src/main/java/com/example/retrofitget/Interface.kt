package com.example.retrofitget

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface Interface {
    @GET("social")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    fun getData(): Call<Data>
}