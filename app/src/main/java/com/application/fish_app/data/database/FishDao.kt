package com.application.fish_app.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FishDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFish(fish: Fish)

    @Query("SELECT * FROM fish WHERE id = :id")
    fun getFish(id: Int): Fish

    @Query("SELECT * FROM fish")
    fun getAllFishes(): List<Fish>

    @Delete
    fun deleteFish(fish: Fish)

    @Query("UPDATE fish SET fishName = :fishName, amount = :amount, fishColor = :fishColor, price = :price WHERE id = :id")
    fun updateFish(id: Int, fishName: String, amount: String, fishColor: String, price: String)
}