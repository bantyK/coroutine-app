package com.banty.demo.ui.weather.current

import androidx.lifecycle.ViewModel
import com.banty.demo.data.repository.WeatherRepository
import com.banty.demo.helper.UnitSystem
import com.banty.demo.helper.deferredLazy

class CurrentWeatherViewModel(private val weatherRepository: WeatherRepository) : ViewModel() {

    private val unitSystem = UnitSystem.METRIC // this will change later

    val isMetric: Boolean
        get() = unitSystem == UnitSystem.METRIC


    val weather by deferredLazy {
        weatherRepository.getCurrentWeather(isMetric)
    }

}
