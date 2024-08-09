package com.example.flightsearchapp.ui.screens.search

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.flightsearchapp.FlightApplication
import com.example.flightsearchapp.data.Airports
import com.example.flightsearchapp.data.Favorite
import com.example.flightsearchapp.data.FlightRepository
import com.example.flightsearchapp.data.UserPreferencesRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchViewModel(
    val flightRepository: FlightRepository,
    private val userPreferencesRepository: UserPreferencesRepository
): ViewModel() {

    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState = _uiState.asStateFlow()
    private var deleteRecord: Favorite? = null
    ;
    private var getAirportsJob: Job? = null

    private var airportList= mutableListOf<Airports>()
    private var favoriteList = mutableListOf<Favorite>()

    init {
        viewModelScope.launch {
            userPreferencesRepository.userPreferencesFlow.collect {
                processSearchQueryFlow(it.searchValue)
            }
        }
    }
    fun onQueryChange(query: String) {
        processSearchQueryFlow(query)
    }
    private fun processSearchQueryFlow(query: String) {
        _uiState.update { it.copy(searchQuery = query) }
        if (query.isEmpty()){
            viewModelScope.launch {
                airportList = flightRepository.getAllAirports().toMutableStateList()
                favoriteList = flightRepository.getAllFavoritesFlights().toMutableStateList()
                _uiState.update {

                    uiState.value.copy(
                        airportList = airportList,
                        favoriteList = favoriteList
                    )
                }
                }
        }
        else{
            getAirportsJob?.cancel()

            getAirportsJob = flightRepository.getAllAirportsFlow(query)
                .onEach { result->
                    _uiState.update {
                        uiState.value.copy(
                            airportList = result,
                        )
                    }
                }.launchIn(viewModelScope)
        }
    }
    fun updateQuery(searchQuery: String) {
        _uiState.update { currentState ->
            currentState.copy(
                searchQuery = searchQuery,
            )
        }
    }
    fun updateSelectedCode(selectedCode: String) {
        _uiState.update { currentState ->
            currentState.copy(
                selectedCode = selectedCode,
            )
        }
    }
    fun removeDbFavorite(record: Favorite){
        viewModelScope.launch {
            deleteRecord = record

            flightRepository.deleteFavoriteFlight(record)

            val dd = uiState.value.favoriteList.toMutableList()

            dd.remove(record)
            _uiState.update {
                uiState.value.copy(
                    favoriteList = dd
                )
            }
        }
    }
    fun updatePreferenceSearchValue(newValue: String) {
        viewModelScope.launch {
            userPreferencesRepository.updateUserPreferences(searchValue = newValue)
        }
    }

    companion object {
        val  Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as FlightApplication)
                val flightRepository = application.container.flightRepository
                val preferencesRepository = application.userPreferencesRepository
                SearchViewModel(
                    flightRepository = flightRepository,
                    userPreferencesRepository = preferencesRepository
                )
            }
        }
    }

}