package com.example.weather.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "forecast_table")
data class ForecastEntity(
    @PrimaryKey(autoGenerate = false)
    val cityName: String,
    val forecastJson: String, // JSON string of forecast data
    val lastUpdated: Long
)
