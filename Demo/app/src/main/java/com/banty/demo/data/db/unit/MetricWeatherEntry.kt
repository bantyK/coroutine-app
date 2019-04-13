package com.banty.demo.data.db.unit

import androidx.room.ColumnInfo

/**
 * Created by Banty on 2019-04-13.
 */
data class MetricWeatherEntry(
        @ColumnInfo(name = "tempC")
        override val temperature: Double,

        @ColumnInfo(name = "condition_text")
        override val conditionText: String,

        @ColumnInfo(name = "condition_icon")
        override val conditionIconUrl: String,

        @ColumnInfo(name = "windKph")
        override val windSpeed: Double,

        @ColumnInfo(name = "windDir")
        override val windDirection: String,

        @ColumnInfo(name = "precipMm")
        override val precipitationVolume: Double,

        @ColumnInfo(name = "feelslikeC")
        override val feelsLikeTemperature: Double,

        @ColumnInfo(name = "visKm")
        override val visibilityDistance: Double

) : StandardWeatherEntry {
}