package com.example.flightsearchapp.ui.screens.flight_screen

import com.example.flightsearchapp.data.Airports
import com.example.flightsearchapp.data.Favorite

data class FlightUiState(
    val code: String = "",
    val favoriteList: List<Favorite> = emptyList(),
    val destinationList: List<Airports> = emptyList(),
    val departureAirport: Airports = Airports(),

)