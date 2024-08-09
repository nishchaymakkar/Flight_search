package com.example.flightsearchapp.data

import androidx.compose.ui.res.stringResource
import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.flightsearchapp.data.UserPreferencesKeys.SEARCH_VALUE
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

data class UserPreferences(
    val searchValue: String ="",
)

object UserPreferencesKeys {
    val SEARCH_VALUE = stringPreferencesKey("search_value")
}

class UserPreferencesRepository(private val dataStore: DataStore<Preferences>) {
    suspend fun updateUserPreferences(
        searchValue: String,
    ){
        dataStore.edit {preferences ->
            preferences[SEARCH_VALUE] = searchValue
        }
    }
    val userPreferencesFlow: Flow<UserPreferences> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {

            }else {
                throw exception
            }
        }.map {preferences ->
            UserPreferences(
                searchValue = preferences[SEARCH_VALUE] ?: "",
            )

        }
}