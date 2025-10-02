package com.example.weather.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.weather.viewmodel.WeatherState
import com.example.weather.viewmodel.WeatherViewModel
import androidx.navigation.NavController
import com.example.weather.data.local.WeatherEntity
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun CurrentWeatherScreen(
    viewModel: WeatherViewModel,
    apiKey: String,
    navController: NavController,
    defaultCity: String = "Ajmer"
) {
    var city by rememberSaveable { mutableStateOf(defaultCity) }

    LaunchedEffect(city) {
        viewModel.fetchWeather(city, apiKey)
    }

    val state by viewModel.weatherState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xFF0D47A1), Color(0xFF42A5F5))
                )
            )
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        WeatherTopBar(city = city) {
            viewModel.fetchWeather(city, apiKey)
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = city,
            onValueChange = { city = it },
            label = { Text("🔍 Search City") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            shape = RoundedCornerShape(16.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.White,
                unfocusedBorderColor = Color.LightGray,
                textColor = Color.White,
                cursorColor = Color.White,
                focusedLabelColor = Color.White
            )
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = { viewModel.fetchWeather(city, apiKey) },
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF42A5F5))
            ) {
                Text("Search", color = Color.White, fontWeight = FontWeight.Bold)
            }

            Button(
                onClick = { navController.navigate("forecast/$city") },
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF1565C0))
            ) {
                Text("7-Day Forecast", color = Color.White, fontWeight = FontWeight.Bold)
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        when (state) {
            is WeatherState.Loading -> CircularProgressIndicator(color = Color.White)
            is WeatherState.Success -> WeatherCard((state as WeatherState.Success).weather)
            is WeatherState.Error -> Text(
                text = (state as WeatherState.Error).message,
                color = Color.Red,
                fontWeight = FontWeight.Bold
            )
            else -> Text(
                text = "No cached data available",
                color = Color.LightGray,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun WeatherTopBar(city: String, onRefresh: () -> Unit) {
    TopAppBar(
        title = { Text(text = "☁ $city", fontWeight = FontWeight.Bold, fontSize = 20.sp) },
        backgroundColor = Color(0xFF1976D2),
        contentColor = Color.White,
        elevation = 8.dp,
        actions = {
            IconButton(onClick = onRefresh) {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = "Refresh",
                    tint = Color.White
                )
            }
        }
    )
}

@Composable
fun WeatherCard(weather: WeatherEntity) {
    Card(
        shape = RoundedCornerShape(20.dp),
        elevation = 12.dp,
        backgroundColor = Color.White.copy(alpha = 0.9f),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // City
            Text(
                text = weather.cityName,
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF0D47A1)
            )
            Spacer(modifier = Modifier.height(10.dp))

            // Icon
            AsyncImage(
                model = "https:${weather.conditionIcon}",
                contentDescription = weather.conditionText,
                modifier = Modifier.size(80.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Temp & Condition
            Text(text = "${weather.tempC.toInt()}°C", fontSize = 36.sp, fontWeight = FontWeight.ExtraBold)
            Text(text = weather.conditionText, fontSize = 18.sp, fontWeight = FontWeight.SemiBold, color = Color.Gray)

            Spacer(modifier = Modifier.height(20.dp))

            // Row 1
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                WeatherInfoItem("🌬 Wind", "${weather.windKph} kph")
                WeatherInfoItem("☀ UV", "${weather.uv}")
                WeatherInfoItem("💧 Humidity", "${weather.humidity}%")
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Row 2
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                WeatherInfoItem("🌫 Air Quality", "${weather.airQuality ?: "N/A"}")
                WeatherInfoItem("👁 Visibility", "${weather.visibility ?: "N/A"} km")
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Row 3
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                WeatherInfoItem("📅 Date", weather.lastUpdated.toDateString(weather.timezone))
                WeatherInfoItem("⏰ Time", weather.lastUpdated.toTimeString(weather.timezone))
            }
        }
    }
}

@Composable
fun WeatherInfoItem(label: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = label, fontSize = 14.sp, fontWeight = FontWeight.Medium, color = Color(0xFF555555))
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = value, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color(0xFF0D47A1))
    }
}

// Date & Time formatters
fun Long.toTimeString(timezone: String): String {
    val sdf = SimpleDateFormat("hh:mm a", Locale.getDefault())
    sdf.timeZone = TimeZone.getTimeZone(timezone)
    return sdf.format(Date(this))
}

fun Long.toDateString(timezone: String): String {
    val sdf = SimpleDateFormat("EEE, dd MMM yyyy", Locale.getDefault())
    sdf.timeZone = TimeZone.getTimeZone(timezone)
    return sdf.format(Date(this))
}
