package com.rmakiyama.ohtenkiapp.ui.weather

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CurrentWeatherResponse(
    @Json(name = "id")
    val id: Long,
    @Json(name = "name")
    val name: String,
    @Json(name = "weather")
    val weather: List<Weather>,
    @Json(name = "main")
    val main: Main
) {

    @JsonClass(generateAdapter = true)
    data class Weather(
        @Json(name = "id")
        val id: Long,
        @Json(name = "main")
        val main: String,
        @Json(name = "description")
        val description: String,
        @Json(name = "icon")
        val icon: String
    )

    @JsonClass(generateAdapter = true)
    data class Main(
        @Json(name = "temp")
        val temperature: Float,
        @Json(name = "temp_min")
        val minTemperature: Float,
        @Json(name = "temp_max")
        val maxTemperature: Float
    )
}
