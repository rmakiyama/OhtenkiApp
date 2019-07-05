package com.rmakiyama.ohtenkiapp.ui.weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rmakiyama.ohtenkiapp.BuildConfig
import com.rmakiyama.ohtenkiapp.R
import com.squareup.moshi.Moshi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import timber.log.Timber

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
        val retrofit = Retrofit.Builder()
            .baseUrl("http://api.openweathermap.org/data/2.5/")
            .addConverterFactory(MoshiConverterFactory.create(Moshi.Builder().build()))
            .build()
        val api = retrofit.create(OpenWeatherApi::class.java)
        api.getCurrentWeather(
            id = KANAGAWA_ID,
            appId = BuildConfig.API_KEY
        ).enqueue(object : Callback<CurrentWeatherResponse> {

            override fun onResponse(call: Call<CurrentWeatherResponse>, response: Response<CurrentWeatherResponse>) {
                Timber.d("response = ${response.body()}")
            }

            override fun onFailure(call: Call<CurrentWeatherResponse>, t: Throwable) {
                Timber.e(t)
            }

        })
    }
}
