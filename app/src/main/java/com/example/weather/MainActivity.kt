package com.example.weather.ui

import ForecastDay
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.weather.data.local.WeatherDatabase
import com.example.weather.data.remote.RetrofitInstance
import com.example.weather.data.repository.WeatherRepository
import com.example.weather.viewmodel.WeatherViewModel
import com.example.weather.viewmodel.WeatherViewModelFactory

class MainActivity : ComponentActivity() {

    private val apiKey = "28c08ae207584bdfb6685820250110"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val dao = WeatherDatabase.getDatabase(this).weatherDao()
        val repository = WeatherRepository(RetrofitInstance.api, dao)
        val factory = WeatherViewModelFactory(repository)

        setContent {
            val weatherViewModel: WeatherViewModel = viewModel(factory = factory)
            val navController = rememberNavController()

            MaterialTheme {
                Surface(color = MaterialTheme.colors.background) {

                    NavHost(
                        navController = navController,
                        startDestination = "current_weather"
                    ) {
                        // Current Weather Screen
                        composable("current_weather") {
                            CurrentWeatherScreen(
                                viewModel = weatherViewModel,
                                apiKey = apiKey,
                                navController = navController
                            )
                        }

                        // 7-Day Forecast Screen
                        composable("forecast/{city}") { backStackEntry ->
                            val city = backStackEntry.arguments?.getString("city") ?: "Ajmer"
                            val forecast = remember { mutableStateOf<List<ForecastDay>>(emptyList()) }

                            LaunchedEffect(city) {
                                val response = repository.getForecast(city, apiKey)
                                response?.forecast?.forecastday?.let {
                                    forecast.value = it
                                }
                            }

                            ForecastScreen(forecastDays = forecast.value, city = city)
                        }
                    }
                }
            }
        }
    }
}
