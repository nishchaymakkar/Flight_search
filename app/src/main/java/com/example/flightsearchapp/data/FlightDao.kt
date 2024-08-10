package com.lixoten.flightsearch.data

import androidx.room.*
import com.example.flightsearchapp.data.Airports
import com.example.flightsearchapp.data.Favorite
import kotlinx.coroutines.flow.Flow

@Dao
interface FlightDao {
    @Query("SELECT * FROM favorite ORDER BY id ASC")
    suspend fun getAllFavorites(): List<Favorite>

    @Query("SELECT * FROM favorite ORDER BY id ASC")
    fun getAllFavoritesFlow(): Flow<List<Favorite>>

    @Query("SELECT * FROM favorite WHERE departure_code = :departureCode AND destination_code = :destinationCode")
    suspend fun getSingleFavorite(departureCode: String, destinationCode: String): Favorite

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteFlight(flight: Favorite)

    @Delete
    suspend fun deleteFavoriteFlight(flight: Favorite)

    @Query("SELECT * FROM airport ORDER BY id ASC")
    fun getAllAirportsFlow(): Flow<List<Airports>>

    @Query("SELECT * FROM airport ORDER BY id ASC")
    suspend fun getAllAirports(): List<Airports>

    @Query("SELECT * FROM airport WHERE iata_code = :query OR name LIKE '%' || :query || '%' ORDER BY name ASC")
    fun getAllAirportsFlow(query: String): Flow<List<Airports>>

    @Query("SELECT * FROM airport WHERE iata_code = :query OR name LIKE '%' || :query || '%' ORDER BY name ASC")
    suspend fun getAllAirports(query: String): List<Airports>

    @Query("SELECT * FROM airport WHERE iata_code = :code")
    suspend fun getAirportByCode(code: String): Airports

    @Query("SELECT * FROM airport WHERE iata_code = :code")
    fun getAirportByCodeFlow(code: String): Flow<Airports>

    @Query("SELECT * FROM airport WHERE id = :id")
    suspend fun getAirportById(id: Int): Airports
}