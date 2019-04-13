package com.banty.demo.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.banty.demo.data.db.entity.CurrentWeatherEntry

/**
 * Created by Banty on 2019-04-13.
 */
@Database(
        entities = [CurrentWeatherEntry::class],
        version = 1
)
abstract class WeatherDatabase : RoomDatabase() {

    abstract fun currentWeatherDao(): CurrentWeatherDao

    companion object {

        @Volatile
        private var instance: WeatherDatabase? = null

        private val lock = Any()

        operator fun invoke(context: Context) =
                instance ?: synchronized(lock) {
                    instance ?: buildDatabase(context).also { instance = it }
                }

        private fun buildDatabase(context: Context) =
                Room.databaseBuilder(context.applicationContext,
                        WeatherDatabase::class.java, "weather_data.db")
                        .build()

    }
}