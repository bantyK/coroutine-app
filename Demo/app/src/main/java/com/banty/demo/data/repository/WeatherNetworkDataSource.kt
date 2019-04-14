package com.banty.demo.data.repository

import androidx.lifecycle.LiveData
import com.banty.demo.data.db.entity.CurrentWeatherEntry
import com.banty.demo.data.network.response.CurrentWeatherResponse

/**
 * Created by Banty on 2019-04-13.
 */
interface WeatherNetworkDataSource {

    val downloadedCurrentWeatherData : LiveData<CurrentWeatherResponse>

    /**
     * Downloads the current weather data from the apixu weather API
    * */
    suspend fun fetchWeatherDataFromNetwork(location:String)
}