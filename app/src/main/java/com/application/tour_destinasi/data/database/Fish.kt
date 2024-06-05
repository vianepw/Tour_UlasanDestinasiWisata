package com.application.tour_destinasi.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fish")
data class Fish(
    @ColumnInfo(name = "fishName")
    var fishName: String,

    @ColumnInfo(name = "amount")
    var amount: String,

    @ColumnInfo(name = "fishColor")
    var fishColor: String,

    @ColumnInfo(name = "price")
    var price: String,

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)