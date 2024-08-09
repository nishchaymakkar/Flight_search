package com.example.flightsearchapp.data.localdata

import com.example.flightsearchapp.data.Airports

object MockData {

    val airports = listOf(
        Airports(
            id = 1,
            iataCode = "OPO",
            name = "Francisco Sá Carne Airport",
            passengers = 5053134,
        ),
        Airports(
            id = 2,
            iataCode = "SAA",
            name = "Stockholm land Airport",
            passengers = 7494765,
        ),
        Airports(
            id = 3,
            iataCode =  "WAW",
            name = "Warsaw Chopin Airport",
            passengers = 18860000,
        ),
        Airports(
            id = 4,
            iataCode = "MRS",
            name = "Marseille Provence Airport",
            passengers = 10151743,
        ),
        Airports(
            id = 5,
            iataCode = "BGY",
            name = "Milan Berg Airport",
            passengers = 3833063,
        ),
    )
}