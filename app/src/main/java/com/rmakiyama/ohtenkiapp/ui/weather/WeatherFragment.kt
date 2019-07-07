package com.rmakiyama.ohtenkiapp.ui.weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.rmakiyama.ohtenkiapp.BuildConfig
import com.rmakiyama.ohtenkiapp.R
import com.squareup.moshi.Moshi
import kotlinx.android.synthetic.main.fragment_weather.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import timber.log.Timber
import kotlin.math.round

class WeatherFragment : Fragment() {

    companion object {
        private const val KANAGAWA_ID: Long = 1860293

        fun newInstance() = WeatherFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_weather, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getCurrentWeather()
    }

    private fun getCurrentWeather() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .addConverterFactory(MoshiConverterFactory.create(Moshi.Builder().build()))
            .build()
        val api = retrofit.create(OpenWeatherApi::class.java)
        api.getCurrentWeather(
            id = KANAGAWA_ID,
            appId = BuildConfig.API_KEY
        ).enqueue(object : Callback<CurrentWeatherResponse> {

            override fun onResponse(call: Call<CurrentWeatherResponse>, response: Response<CurrentWeatherResponse>) {
                val response = response.body() ?: return
                setBackgroundColor(response.weather.firstOrNull()?.id ?: 0)
                weather.text = response.weather.firstOrNull()?.main ?: "Unknown"
                location.text = response.name
                temperature.text = response.main.temperature.toCelsius().toString()
                Glide.with(this@WeatherFragment)
                    .load("https://openweathermap.org/img/wn/${response.weather.firstOrNull()?.icon}@2x.png")
                    .into(weather_icon)
            }

            override fun onFailure(call: Call<CurrentWeatherResponse>, t: Throwable) {
                Timber.e(t)
            }

        })
    }

    private fun setBackgroundColor(id: Long) {
        when {
            id == 800L -> background_top.setBackgroundResource(R.color.background_sunny)
            id > 800L -> background_top.setBackgroundResource(R.color.background_cloudy)
            else -> background_top.setBackgroundResource(R.color.background_rainy)
        }
    }

    private fun Float.toCelsius(): Float {
        return round((this - 273.15F) * 10) / 10
    }
}
