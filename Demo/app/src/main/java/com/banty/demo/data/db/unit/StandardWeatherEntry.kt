package com.banty.demo.data.db.unit

/**
 * Created by Banty on 2019-04-13.
 */
interface StandardWeatherEntry {
    val temperature: Double
    val conditionText: String
    val conditionIconUrl: String
    val windSpeed: Double
    val windDirection: String
    val precipitationVolume: Double
    val feelsLikeTemperature: Double
    val visibilityDistance: Double
}