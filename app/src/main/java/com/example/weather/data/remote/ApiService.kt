package com.example.weather.data.remote

import com.example.weather.data.models.Tiempo5DiasResponse
import com.example.weather.data.models.TiempoActualResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("data/2.5/weather?")
    suspend fun getTiempoActual(
        @Query("lat") lat: Int = ApiRest.lat,
        @Query("lon") lon: Int = ApiRest.lon,
        @Query("lang") lang: String = ApiRest.lang,
        @Query("appid") appid: String = ApiRest.appid,
        @Query("units") units: String = ApiRest.units,
    ): Response<TiempoActualResponse>
    @GET("data/2.5/forecast?")
    suspend fun getTiempo5Dias(
        @Query("lat") lat: Int = ApiRest.lat,
        @Query("lon") lon: Int = ApiRest.lon,
        @Query("lang") lang: String = ApiRest.lang,
        @Query("appid") appid: String = ApiRest.appid,
        @Query("units") units: String = ApiRest.units,
    ): Response<Tiempo5DiasResponse>
}