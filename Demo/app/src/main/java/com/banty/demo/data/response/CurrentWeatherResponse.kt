package com.banty.demo.data.response

import com.banty.demo.data.db.entity.CurrentWeatherEntry
import com.banty.demo.data.db.entity.Location
import com.google.gson.annotations.SerializedName

data class CurrentWeatherResponse(
        @SerializedName("current")
        val currentWeatherEntry: CurrentWeatherEntry,
        val location: Location
)