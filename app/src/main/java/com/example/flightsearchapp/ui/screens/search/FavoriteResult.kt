package com.example.flightsearchapp.ui.screens.search

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.flightsearchapp.data.Airports
import com.example.flightsearchapp.data.Favorite
import com.example.flightsearchapp.ui.screens.flight_screen.FlightRow

@Composable
fun FavoriteResult(
    modifier: Modifier = Modifier,
    airportsList:List<Airports>,
    favoriteList: List<Favorite>,
    ovFavoriteClick: (String, String) -> Unit
) {
    LazyColumn(
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        items(favoriteList, key = {it.id}){item ->
            val departAirport = airportsList.first{airports ->
                airports.iataCode == item.departureCode}
            val destinationAriport = airportsList.first {
                airports -> airports.iataCode == item.destinationCode
            }
            FlightRow(
                isFavorite = true,
                departureAirportCode = departAirport.iataCode,
                departureAirportName = departAirport.name,
                destinationAirportCode = destinationAriport.iataCode,
                destinationAirportName = destinationAriport.name,
                onFavoriteClick = ovFavoriteClick
            )
        }
    }

}