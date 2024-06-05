package com.application.tour_destinasi.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Fish::class],
    version = 1
)
abstract class FishDatabase : RoomDatabase() {

    abstract fun fishDao(): FishDao

    companion object {
        @Volatile
        private var INSTANCE: FishDatabase? = null

        fun getInstance(context: Context): FishDatabase {
            INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FishDatabase::class.java,
                    "fish_db"
                ).build()
                INSTANCE = instance
                instance
            }
            return INSTANCE as FishDatabase
        }
    }
}