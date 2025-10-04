package com.example.weather.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather_table")
data class WeatherEntity(
    @PrimaryKey(autoGenerate = false)
    val cityName: String,
    val tempC: Float,
    val conditionText: String,
    val conditionIcon: String,
    val windKph: Float,
    val uv: Float,
    val humidity: Int,
    val visibility: Float? = null,
    val airQuality: Int? = null,
    val sunrise: String? = null,
    val sunset: String? = null,
<<<<<<< HEAD
    val lastUpdated: Long,
    val timezone: String
=======
    val lastUpdated: Long, // timestamp
    val timezone: String // from API location.tz_id
>>>>>>> fe93859b9e72090f216dc5eaccf480caf68f3034

)
