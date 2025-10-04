data class WeatherResponse(
    val location: Location,
    val current: Current,
    val forecast: Forecast? = null
)

data class Location(
    val name: String,
    val region: String,
    val country: String,
    val localtime: String,
    val tz_id: String
)

data class Current(
    val temp_c: Float,
    val condition: Condition,
    val wind_kph: Float,
    val uv: Float,
    val vis_km: Float,
    val humidity: Int,
<<<<<<< HEAD
    val sunrise: String? = null,
    val sunset: String? = null,
    val air_quality: AirQuality? = null
=======
    val sunrise: String? = null,    // new
    val sunset: String? = null,
    val air_quality: AirQuality? = null// new
>>>>>>> fe93859b9e72090f216dc5eaccf480caf68f3034
)

data class AirQuality(
    val co: Float?,
    val pm2_5: Float?,
    val pm10: Float?
)

data class Condition(
    val text: String,
    val icon: String
)

data class Forecast(
    val forecastday: List<ForecastDay>
)

data class ForecastDay(
    val date: String,
    val day: Day,
<<<<<<< HEAD
    val astro: Astro? = null
=======
    val astro: Astro? = null // sunrise/sunset usually here
>>>>>>> fe93859b9e72090f216dc5eaccf480caf68f3034
)

data class Day(
    val maxtemp_c: Float,
    val mintemp_c: Float,
    val avgtemp_c: Float,
    val condition: Condition
)

data class Astro(
    val sunrise: String,
    val sunset: String
)
