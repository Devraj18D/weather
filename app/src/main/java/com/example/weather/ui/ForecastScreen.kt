package com.example.weather.ui

import ForecastDay
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter

@Composable
fun ForecastScreen(forecastDays: List<ForecastDay>, city: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "🌤️ 7-Day Forecast for $city",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF0D47A1)
        )
        Spacer(modifier = Modifier.height(12.dp))

        if (forecastDays.isEmpty()) {
            Text("No forecast data available", color = Color.Red)
        } else {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                items(forecastDays) { day ->
                    ForecastCard(day)
                }
            }
        }
    }
}

@Composable
fun ForecastCard(day: ForecastDay) {
    Card(
        shape = RoundedCornerShape(20.dp),
        elevation = 10.dp,
        backgroundColor = Color(0xFFFAFAFA),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp, vertical = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // 📅 Date + Sunrise/Sunset + Temps
            Column(
                verticalArrangement = Arrangement.spacedBy(6.dp),
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = day.date,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color(0xFF1565C0)
                )
                Text(
                    text = "🌥️ ${day.day.condition.text}",
                    fontSize = 15.sp,
                    color = Color(0xFF444444)
                )
                Text(
                    text = "⬆️ ${day.day.maxtemp_c.toInt()}°C   ⬇️ ${day.day.mintemp_c.toInt()}°C",
                    fontSize = 14.sp,
                    color = Color.DarkGray
                )
                Text(
                    text = "🌡 Avg: ${day.day.avgtemp_c.toInt()}°C",
                    fontSize = 14.sp,
                    color = Color(0xFF1E88E5),
                    fontWeight = FontWeight.Medium
                )
            }

            // 🌤 Icon
            Image(
                painter = rememberAsyncImagePainter("https:${day.day.condition.icon}"),
                contentDescription = day.day.condition.text,
                modifier = Modifier.size(60.dp),
                contentScale = ContentScale.Fit
            )
        }
    }
}
