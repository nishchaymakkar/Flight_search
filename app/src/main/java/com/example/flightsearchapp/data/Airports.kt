package com.example.flightsearchapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "airport")
data class Airports(
    @PrimaryKey
    val id: Int = 0,
    @ColumnInfo(name = "iata_code")
    val iataCode: String = "",
    @ColumnInfo(name = "name")
    val name: String = "",
    @ColumnInfo(name = "passengers")
    val passengers: Int =0
)
