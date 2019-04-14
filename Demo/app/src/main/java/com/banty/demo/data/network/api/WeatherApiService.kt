package com.banty.demo.data.network.api

import com.banty.demo.data.network.interceptor.ConnectivityInterceptor
import com.banty.demo.data.network.response.CurrentWeatherResponse
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Banty on 2019-04-13.
 */

const val APIXU_API_KEY = "869dd02e266c47eb8a172218190704"

interface WeatherApiService {

    @GET("current.json")
    fun getCurrentWeather(@Query("q") location: String): Deferred<CurrentWeatherResponse>


    companion object {
        operator fun invoke(connectivityInterceptor: ConnectivityInterceptor): WeatherApiService {

            val requestInterceptor = getRequestInterceptor()

            val okHttpClient = getHttpClient(requestInterceptor, connectivityInterceptor)

            return getRetrofitClient(okHttpClient)
        }

        private fun getRetrofitClient(okHttpClient: OkHttpClient) =
                Retrofit.Builder()
                        .client(okHttpClient)
                        .baseUrl("https://api.apixu.com/v1/")
                        .addCallAdapterFactory(CoroutineCallAdapterFactory())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                        .create(WeatherApiService::class.java)


        private fun getHttpClient(requestInterceptor: Interceptor, connectivityInterceptor: ConnectivityInterceptor): OkHttpClient {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY

            return OkHttpClient.Builder()
                    .addInterceptor(requestInterceptor)
                    .addInterceptor(logging)
                    .addInterceptor(connectivityInterceptor)
                    .build()
        }


        private fun getRequestInterceptor(): Interceptor {
            return Interceptor { chain ->
                val url = chain.request()
                        .url()
                        .newBuilder()
                        .addQueryParameter("key", APIXU_API_KEY)
                        .build()

                val request = chain.request()
                        .newBuilder()
                        .url(url)
                        .build()

                return@Interceptor chain.proceed(request)
            }
        }
    }
}