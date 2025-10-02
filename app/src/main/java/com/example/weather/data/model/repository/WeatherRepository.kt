package com.example.weather.data.repository

import WeatherResponse
import com.example.weather.data.local.WeatherDao
import com.example.weather.data.local.WeatherEntity
import com.example.weather.data.remote.WeatherApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WeatherRepository(
    private val api: WeatherApi,
    private val dao: WeatherDao
) {

    // ✅ Current weather fetch (API + fallback to DB)
    suspend fun getWeather(city: String, apiKey: String): WeatherEntity? {
        return withContext(Dispatchers.IO) {
            try {
                val response: WeatherResponse = api.getCurrentWeather(apiKey, city)

                // ✅ Average air quality calculation
//                val aq = response.current.air_quality
//                val airQualityAvg = if (aq != null) {
//                    listOfNotNull(aq.co, aq.pm2_5, aq.pm10).average().toFloat()
//                } else null

                val aq = response.current.air_quality
                val airQualityAvg = aq?.let {
                    val list = listOfNotNull(it.co, it.pm2_5, it.pm10)
                    if (list.isNotEmpty()) list.average().toInt() else null
                }

                val entity = WeatherEntity(
                    cityName = response.location.name,
                    tempC = response.current.temp_c,
                    conditionText = response.current.condition.text,
                    conditionIcon = response.current.condition.icon,
                    windKph = response.current.wind_kph,
                    uv = response.current.uv,
                    humidity = response.current.humidity,
                    visibility = response.current.vis_km,
                    airQuality = airQualityAvg,
                    lastUpdated = System.currentTimeMillis(),
                    timezone = response.location.tz_id
                )

                dao.insertWeather(entity)
                entity

            } catch (e: Exception) {
                dao.getWeather(city) // fallback to DB
            }
        }
    }

    // ✅ 7-day forecast fetch
    suspend fun getForecast(city: String, apiKey: String): WeatherResponse? {
        return withContext(Dispatchers.IO) {
            try {
                api.getForecastWeather(apiKey, city, days = 7)
            } catch (e: Exception) {
                null
            }
        }
    }
}
