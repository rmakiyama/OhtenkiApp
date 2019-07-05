package com.rmakiyama.ohtenkiapp.ui.weather

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherApi {

    @GET("weather")
    fun getCurrentWeather(
        @Query("id") id: Long,
        @Query("appid") appId: String
    ): Call<CurrentWeatherResponse>
}
