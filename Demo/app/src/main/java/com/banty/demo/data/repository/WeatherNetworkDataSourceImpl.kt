package com.banty.demo.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.banty.demo.data.network.api.WeatherApiService
import com.banty.demo.data.network.exception.NoNetworkConnectionException
import com.banty.demo.data.network.response.CurrentWeatherResponse

class WeatherNetworkDataSourceImpl(
        private val apiService:WeatherApiService) : WeatherNetworkDataSource {

    private val _downloadedCurrentWeatherData = MutableLiveData<CurrentWeatherResponse>()
    override val downloadedCurrentWeatherData: LiveData<CurrentWeatherResponse>
        get() = _downloadedCurrentWeatherData


    override suspend fun fetchWeatherDataFromNetwork(location: String) {
        try {
            val dataFromApi =
                    apiService.getCurrentWeather(location)
                            .await()

            _downloadedCurrentWeatherData.postValue(dataFromApi)

        }catch (exception:NoNetworkConnectionException) {
            Log.e("Connectivity", "No internet connection", exception)

        }
    }
}