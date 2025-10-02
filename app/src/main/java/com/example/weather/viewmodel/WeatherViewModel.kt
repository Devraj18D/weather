package com.example.weather.viewmodel

//import WeatherEntity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.data.local.WeatherEntity
import com.example.weather.data.repository.WeatherRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

// Weather ke states
sealed class WeatherState {
    object Loading : WeatherState()
    data class Success(val weather: WeatherEntity) : WeatherState()
    data class Error(val message: String) : WeatherState()
    object Empty : WeatherState()   // <-- jab na API aur na DB me data ho
}

class WeatherViewModel(val repository: WeatherRepository) : ViewModel() {

    private val _weatherState = MutableStateFlow<WeatherState>(WeatherState.Loading)
    val weatherState: StateFlow<WeatherState> = _weatherState

    fun fetchWeather(city: String, apiKey: String) {
        _weatherState.value = WeatherState.Loading
        viewModelScope.launch {
            try {
                val weather: WeatherEntity? = repository.getWeather(city, apiKey)
                if (weather != null) {
                    _weatherState.value = WeatherState.Success(weather)
                } else {
                    _weatherState.value = WeatherState.Empty
                }
            } catch (e: Exception) {
                _weatherState.value = WeatherState.Error("Failed to load weather: ${e.message}")
            }
        }
    }
}
