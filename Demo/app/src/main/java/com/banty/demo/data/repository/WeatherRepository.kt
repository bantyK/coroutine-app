package com.banty.demo.data.repository

import androidx.lifecycle.LiveData
import com.banty.demo.data.db.unit.StandardWeatherEntry

/**
 * Created by Banty on 2019-04-14.
 */
interface WeatherRepository {

    suspend fun getCurrentWeather(isMetric: Boolean) : LiveData<out StandardWeatherEntry>
}