package com.application.tour_destinasi.data.database


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "destination")
data class Destination(
    @ColumnInfo(name = "destinationName")
    var destinationName: String,

    @ColumnInfo(name = "amount")
    var amount: String,

    @ColumnInfo(name = "dColor")
    var dColor: String,

    @ColumnInfo(name = "price")
    var price: String,

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)