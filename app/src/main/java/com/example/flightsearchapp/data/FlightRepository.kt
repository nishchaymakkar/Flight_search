package com.example.flightsearchapp.data

import kotlinx.coroutines.flow.Flow

interface FlightRepository {
    fun getAllAirportsFlow(): Flow<List<Airports>>
    fun getAllAirportsFlow(query: String): Flow<List<Airports>>
    fun getAirportByCodeFlow(code: String): Flow<Airports>

    suspend fun getAllAirports(): List<Airports>
    suspend fun getAllAirports(query: String): List<Airports>
    suspend fun getAirportByCode(code: String): Airports

    suspend fun getAirportById(id: Int): Airports

    fun getAllFavoritesFlightsFlow():  Flow<List<Favorite>>
    suspend fun getAllFavoritesFlights(): List<Favorite>
    suspend fun insertFavoriteFlight(flight: Favorite)
    suspend fun deleteFavoriteFlight(flight: Favorite)

    suspend fun getSingleFavorite(departureCode: String, destinationCode: String): Favorite
}