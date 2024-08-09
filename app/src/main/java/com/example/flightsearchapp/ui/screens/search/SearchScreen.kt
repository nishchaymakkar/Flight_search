package com.example.flightsearchapp.ui.screens.search

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.flightsearchapp.NavigationDestination
import com.example.flightsearchapp.R
import com.example.flightsearchapp.data.Favorite

object SearchDestination: NavigationDestination {
    override val route = "home"
    override val titleRes = R.string.app_name
}
@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    onSelectCode: (String) -> Unit
) {
    val viewModel: SearchViewModel = viewModel(factory = SearchViewModel.Factory)
    val uiState = viewModel.uiState.collectAsState().value
    Column(
        modifier = modifier
    ) {


        SearchTextField(
            uiState.searchQuery,
            onQueryChange = {
                viewModel.updateQuery(it)
                viewModel.updateSelectedCode("")
                viewModel.onQueryChange(it)
            }
        )

        if (uiState.searchQuery.isNotEmpty()) {
            val favoriteList = uiState.favoriteList
            val airportList = uiState.airportList

            if (favoriteList.isNotEmpty()) {
                FavoriteResult(
                    airportsList = airportList,
                    favoriteList = favoriteList,
                    ovFavoriteClick = { departureCode: String, destinationCode: String ->
                        val tmp = Favorite(
                            id = favoriteList.filter { ccc ->
                                (ccc.departureCode == departureCode && ccc.destinationCode == destinationCode)
                            }.first().id,
                            departureCode = departureCode,
                            destinationCode = destinationCode
                        )
                        viewModel.removeDbFavorite(tmp)
                    },
                )
            } else {
                Text(text = "No Favorites Yet")
            }
        } else {
            val airports = uiState.airportList

            SearchResult(
                airports = airports,
                onSelectCode = onSelectCode
            )
        }
    }
}