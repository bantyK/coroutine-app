package com.banty.demo.ui.weather.current

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.banty.demo.data.repository.WeatherRepository

/**
 * Created by Banty on 2019-04-14.
 */

class CurrentWeatherViewModelFactory(private val weatherRepository: WeatherRepository) :
        ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CurrentWeatherViewModel(weatherRepository) as T
    }
}