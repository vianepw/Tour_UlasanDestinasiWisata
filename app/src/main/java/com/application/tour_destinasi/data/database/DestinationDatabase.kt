package com.application.tour_destinasi.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Destination::class],
    version = 1
)
abstract class DestinationDatabase : RoomDatabase() {

    abstract fun AppDao(): AppDao

    companion object {
        @Volatile
        private var INSTANCE: DestinationDatabase? = null

        fun getInstance(context: Context): DestinationDatabase {
            INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DestinationDatabase::class.java,
                    "Destination_db"
                ).build()
                INSTANCE = instance
                instance
            }
            return INSTANCE as DestinationDatabase
        }
    }
}