package com.example.weather.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiRest {
    lateinit var service: ApiService
    val URL = "https://api.openweathermap.org/"
    val lat = 42
    val lon = -2
    val units = "metric"
    val lang = "es"
    val appid = "4af34be67a6497bd8750c2a48a52c987"
    init {
        initService()
    }
    private fun initService() {
        val retrofit = Retrofit.Builder()
            .baseUrl( URL)
            .addConverterFactory( GsonConverterFactory .create())
            .build()
        service = retrofit.create(ApiService::class.java)
    }
}
