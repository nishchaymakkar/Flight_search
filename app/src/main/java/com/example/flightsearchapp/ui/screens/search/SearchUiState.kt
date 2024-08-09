package com.example.flightsearchapp.ui.screens.search

import com.example.flightsearchapp.data.Airports
import com.example.flightsearchapp.data.Favorite

data class SearchUiState(
    val searchQuery: String = "",
    val selectedCode: String = "",
    val airportList: List<Airports> = emptyList(),
    val favoriteList: List<Favorite> = emptyList()
)
