package com.example.flightsearchapp.data

import com.lixoten.flightsearch.data.FlightDao
import kotlinx.coroutines.flow.Flow

class OfflineFlightRepository(private val airportsDao: FlightDao): FlightRepository {
    override fun getAllAirportsFlow(): Flow<List<Airports>> {
        return airportsDao.getAllAirportsFlow()
    }
    override fun getAllAirportsFlow(query: String): Flow<List<Airports>> {
        return airportsDao.getAllAirportsFlow(query)
    }
    override fun getAirportByCodeFlow(code: String): Flow<Airports> {
        return airportsDao.getAirportByCodeFlow(code)
    }
    override suspend fun getAllAirports(): List<Airports> {
        return airportsDao.getAllAirports()
    }
    override suspend fun getAllAirports(query: String): List<Airports> {
        return airportsDao.getAllAirports(query)
    }
    override suspend fun getAirportByCode(code: String): Airports {
        return airportsDao.getAirportByCode(code)
    }

    override suspend fun getAirportById(id: Int): Airports {
        return airportsDao.getAirportById(id)
    }

    override fun getAllFavoritesFlightsFlow(): Flow<List<Favorite>> {
        return airportsDao.getAllFavoritesFlow()
    }


    override suspend fun getAllFavoritesFlights(): List<Favorite> {
        return airportsDao.getAllFavorites()
    }
    override suspend fun deleteFavoriteFlight(flight: Favorite) {
        return airportsDao.deleteFavoriteFlight(flight)
    }


    override suspend fun getSingleFavorite(departureCode: String, destinationCode: String): Favorite {
        return airportsDao.getSingleFavorite(departureCode, destinationCode)
    }

    override suspend fun insertFavoriteFlight(flight: Favorite) = airportsDao.insertFavoriteFlight(flight)
}