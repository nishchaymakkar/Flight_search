package com.example.flightsearchapp

import android.app.Application
import android.content.Context
import com.example.flightsearchapp.data.UserPreferencesRepository
import com.example.flightsearchapp.di.AppContainer
import com.example.flightsearchapp.di.AppDataContainer
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.preferencesDataStore
import androidx.datastore.preferences.core.Preferences



private const val LAYOUT_PREFERENCE_NAME = "layout_preferences"
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = LAYOUT_PREFERENCE_NAME
)
class FlightApplication : Application() {

    /**
     * AppContainer instance used by the rest of classes to obtain dependencies
     */
    lateinit var container: AppContainer

    lateinit var userPreferencesRepository: UserPreferencesRepository

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
        userPreferencesRepository = UserPreferencesRepository(dataStore)
    }
}

// notes: code was done differently then the way we leannred in inventory codelab
//class BusScheduleApplication: Application() {
//    val database: AppDatabase by lazy { AppDatabase.getDatabase(this) }
//}