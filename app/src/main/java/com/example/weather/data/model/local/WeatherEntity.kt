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
    val lastUpdated: Long,
    val timezone: String

)
