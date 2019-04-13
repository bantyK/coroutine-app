package com.banty.demo.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.banty.demo.data.db.entity.CURRENT_WEATHER_ID
import com.banty.demo.data.db.entity.CurrentWeatherEntry
import com.banty.demo.data.db.unit.ImperialWeatherEntry
import com.banty.demo.data.db.unit.MetricWeatherEntry

/**
 * Created by Banty on 2019-04-13.
 */
@Dao
interface CurrentWeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(weatherEntity:CurrentWeatherEntry)

    /**
     * Returns the value in metric system
     * */
    @Query("select * from current_weather where id=${CURRENT_WEATHER_ID}")
    fun getWeatherInMetricSystemValues() : LiveData<MetricWeatherEntry>

    /**
     * Returns the value in imperial system
     * */
    @Query("select * from current_weather where id=${CURRENT_WEATHER_ID}")
    fun getWeatherInImperialSystemValues() : LiveData<ImperialWeatherEntry>
}