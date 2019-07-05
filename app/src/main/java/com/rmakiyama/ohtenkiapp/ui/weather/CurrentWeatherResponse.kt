package com.rmakiyama.ohtenkiapp.ui.weather

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CurrentWeatherResponse(
    @Json(name = "id")
    val id: Long,
    @Json(name = "main")
    val main: Main
) {
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
