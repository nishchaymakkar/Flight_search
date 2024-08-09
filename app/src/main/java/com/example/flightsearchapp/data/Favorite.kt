package com.example.flightsearchapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Favorite(
    @PrimaryKey
    val id: Int =0,
    @ColumnInfo(name = "departure_code")
    val departureCode: String = "",
    @ColumnInfo(name = "destination_code")
    val destinationCode: String = ""
)
