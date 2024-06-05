package com.application.destination_app.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AppDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDestination(destination: Destination)

    @Query("SELECT * FROM destination WHERE id = :id")
    fun getDestination(id: Int): Destination

    @Query("SELECT * FROM destination")
    fun getALLDestinations(): List<Destination>

    @Delete
    fun deleteDestination(destination: Destination)

    @Query("UPDATE destination SET destinationName = destinationName, amount = :amount, dColor = dColor, price = :price WHERE id = :id")

    fun updateDestination(id: Int, destination: String, amount: String, dColor: String, price: String)
}