package com.example.flightsearchapp.di

import android.content.Context
import com.example.flightsearchapp.data.FlightDatabase
import com.example.flightsearchapp.data.FlightRepository
import com.example.flightsearchapp.data.OfflineFlightRepository

interface AppContainer {
    val flightRepository: FlightRepository
}

class AppDataContainer(private val context: Context) : AppContainer {
    override val flightRepository: FlightRepository by lazy {
        OfflineFlightRepository(FlightDatabase.getDatabase(context).flightDao())
    }

}