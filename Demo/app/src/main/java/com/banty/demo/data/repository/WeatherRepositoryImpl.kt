package com.banty.demo.data.repository

import androidx.lifecycle.LiveData
import com.banty.demo.data.db.CurrentWeatherDao
import com.banty.demo.data.db.unit.StandardWeatherEntry
import com.banty.demo.data.network.response.CurrentWeatherResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.threeten.bp.ZonedDateTime

class WeatherRepositoryImpl(
        private val weatherDao: CurrentWeatherDao,
        private val weatherNetworkDataSource: WeatherNetworkDataSource
) : WeatherRepository {

    init {
        weatherNetworkDataSource.downloadedCurrentWeatherData.observeForever { downloadedWeatherData ->
            saveWeatherDataLocally(downloadedWeatherData)
        }
    }



    private suspend fun fetchWeatherDataFromNetwork() {
        if (hasCacheExpired(ZonedDateTime.now().minusHours(1))) {
            weatherNetworkDataSource.fetchWeatherDataFromNetwork("Pune")
        }
    }

    private fun hasCacheExpired(lastFetchedTime: ZonedDateTime): Boolean {
        val thirtyMinutesAgo = ZonedDateTime.now().minusMinutes(30)
        return lastFetchedTime.isBefore(thirtyMinutesAgo)
    }

    override suspend fun getCurrentWeather(isMetric: Boolean): LiveData<out StandardWeatherEntry> {
        return withContext(Dispatchers.IO) {
            fetchWeatherDataFromNetwork()
            return@withContext if (isMetric)
                weatherDao.getWeatherInMetricSystemValues()
            else
                weatherDao.getWeatherInImperialSystemValues()
        }
    }

    private fun saveWeatherDataLocally(weatherData: CurrentWeatherResponse) {
        GlobalScope.launch(Dispatchers.IO) {
            weatherDao.insert(weatherData.currentWeatherEntry)
        }
    }
}