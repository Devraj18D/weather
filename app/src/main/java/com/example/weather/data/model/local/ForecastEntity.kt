package com.example.weather.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "forecast_table")
data class ForecastEntity(
    @PrimaryKey(autoGenerate = false)
    val cityName: String,
<<<<<<< HEAD
    val forecastJson: String,
=======
    val forecastJson: String, // JSON string of forecast data
>>>>>>> fe93859b9e72090f216dc5eaccf480caf68f3034
    val lastUpdated: Long
)
