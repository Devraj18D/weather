# 🌤️ WeatherApp – Modern Android Weather Application  

**✨ A Clean, Modern & Scalable Android Weather App built with Jetpack Compose, MVVM, StateFlow, Coroutines, Room Database, and Retrofit. ✨**

![Kotlin](https://img.shields.io/badge/Kotlin-1.9-blueviolet?style=for-the-badge&logo=kotlin)
![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-UI-orange?style=for-the-badge&logo=jetpackcompose)
![MVVM](https://img.shields.io/badge/MVVM-Architecture-brightgreen?style=for-the-badge)
![StateFlow](https://img.shields.io/badge/StateFlow-Reactive-red?style=for-the-badge)
![Room](https://img.shields.io/badge/Room-Database-yellow?style=for-the-badge)
![Retrofit](https://img.shields.io/badge/Retrofit-Networking-green?style=for-the-badge&logo=square)
![Coroutines](https://img.shields.io/badge/Coroutines-Async-blue?style=for-the-badge)

---

## 📑 Overview  

🌍 **WeatherApp** is a modern Android application that delivers **real-time weather conditions** and a **7-day forecast** for any city worldwide.  

💡 Built with **MVVM + Clean Architecture**, it integrates **offline caching (Room)**, **reactive UI (StateFlow)**, and a **beautiful Compose interface**.  

---

## ✨ Features  

- 🔎 **Global Search** → Enter any city name and fetch live weather instantly  
- 🌡️ **Current Weather** → Temperature, Humidity, Wind, UV Index, Air Quality, Visibility  
- 📅 **7-Day Forecast** → Clean cards with min/max temp & conditions  
- 💾 **Offline Cache** → Stores last results in Room DB  
- 🕒 **Local Time/Date** → Accurate city-based local time  
- ⚡ **Reactive UI** → Auto updates with StateFlow  
- 🎨 **Modern Design** → Material 3, gradients, custom icons  
- 🚨 **Error Handling** → Loading, Error & Success states  

---

## 🛠️ Tech Stack  

| 🏗 Layer | ⚡ Technology |
|----------|--------------|
| **📝 Language** | Kotlin (1.9.x) |
| **🎨 UI** | Jetpack Compose (Material 3) |
| **🧩 Architecture** | MVVM + Clean Architecture |
| **🔄 State Management** | ViewModel + StateFlow |
| **⚡ Async Ops** | Kotlin Coroutines |
| **🌐 Networking** | Retrofit + Gson |
| **💾 Local Storage** | Room Database |
| **🖼️ Image Loading** | Coil |
| **☁️ API Provider** | [WeatherAPI](https://www.weatherapi.com/) |
| **🛠 IDE** | Android Studio Ladybug (or newer) |

---

## 🏗️ Architecture  

🛡 **MVVM + Clean Architecture** ensures scalability & testability:  

- 🧑‍💻 **ViewModel** → Manages state & async logic via Coroutines  
- 🔄 **StateFlow** → Reactive streams, Compose recomposes automatically  
- 🗂 **Repository** → Abstracts DB + API logic  
- 💾 **Room DB** → Offline-first storage for last results  
- 🌐 **Retrofit** → Clean API consumption layer  

---

## 📱 UI Design  

### 🏠 Home Screen  
- 🔍 Search bar with city input  
- 🌡 Weather card (icon, temp, condition, AQI, visibility)  
- 🕒 Local date & time  

### 📅 Forecast Screen  
- 📊 Daily forecast list with cards  
- 🌞 Shows date, min/max temp, icons  
- 🎨 Styled with Material 3  

---

## 📷 Screenshots  

| ![Screenshot1](https://github.com/user-attachments/assets/36735b0f-82ee-4879-9ab8-9ce72ccd516a) | ![Screenshot2](https://github.com/user-attachments/assets/b82d77f9-3907-438a-ba87-6865fb040482) | ![Screenshot3](https://github.com/user-attachments/assets/8ad78e2c-5219-4883-a1b5-26ab214813d3) | ![Screenshot_2025-10-04-13-35-35-84_067ca54d029a1c65bea8d27590265e45](https://github.com/user-attachments/assets/d851fcfd-6cc2-40dc-b0db-7db539792055) |
|---|---|---|---|







---


