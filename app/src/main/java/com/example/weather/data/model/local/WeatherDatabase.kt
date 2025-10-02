package com.example.weather.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * WeatherDatabase: The main entry point for the Room database.
 * * NOTE: The version is set to 3 to resolve the subsequent schema change error.
 * WeatherEntity and WeatherDao are assumed to be defined in this package.
 */
@Database(entities = [WeatherEntity::class], version = 4) // <-- Version updated to 3
abstract class WeatherDatabase : RoomDatabase() {

    // Abstract function to get the DAO (Data Access Object)
    abstract fun weatherDao(): WeatherDao

    companion object {
        // Volatile instance ensures the value is always up-to-date and visible to all threads
        @Volatile
        private var INSTANCE: WeatherDatabase? = null

        fun getDatabase(context: Context): WeatherDatabase {
            // If INSTANCE is not null, then return it, otherwise create a new database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WeatherDatabase::class.java,
                    "weather_db"
                )
                    // Allows Room to rebuild the database if schema changes and no migration is defined.
                    // This fixes the 'identity hash' error by discarding old data and creating a fresh DB.
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
// Assume WeatherEntity.kt and WeatherDao.kt exist in this package.
// Example definitions (not part of this file, but needed for context):
// @Entity(tableName = "weather_table")
// data class WeatherEntity(...)
//
// @Dao
// interface WeatherDao { ... }
