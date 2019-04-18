package com.banty.demo

import android.app.Application
import com.banty.demo.data.db.WeatherDatabase
import com.banty.demo.data.network.api.WeatherApiService
import com.banty.demo.data.network.interceptor.ConnectivityInterceptor
import com.banty.demo.data.network.interceptor.ConnectivityInterceptorImpl
import com.banty.demo.data.provider.UnitProvider
import com.banty.demo.data.provider.UnitProviderImpl
import com.banty.demo.data.repository.WeatherNetworkDataSource
import com.banty.demo.data.repository.WeatherNetworkDataSourceImpl
import com.banty.demo.data.repository.WeatherRepository
import com.banty.demo.data.repository.WeatherRepositoryImpl
import com.banty.demo.ui.weather.current.CurrentWeatherViewModelFactory
import com.jakewharton.threetenabp.AndroidThreeTen
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

/**
 * Created by Banty on 2019-04-14.
 */
class WeatherApp : Application(), KodeinAware {

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
    }
    override val kodein = Kodein.lazy {
        import(androidXModule(this@WeatherApp))

        bind() from singleton { WeatherDatabase(instance()) }
        bind() from singleton { instance<WeatherDatabase>().currentWeatherDao() }
        bind<ConnectivityInterceptor>() with singleton { ConnectivityInterceptorImpl(instance()) }
        bind() from singleton { WeatherApiService(instance()) }
        bind<WeatherNetworkDataSource>() with singleton { WeatherNetworkDataSourceImpl(instance()) }
        bind<WeatherRepository>() with singleton { WeatherRepositoryImpl(instance(), instance()) }
        bind<UnitProvider>() with singleton { UnitProviderImpl(instance()) }
        bind() from provider { CurrentWeatherViewModelFactory(instance(), instance()) }
    }

}