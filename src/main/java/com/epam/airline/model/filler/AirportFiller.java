package com.epam.airline.model.filler;

import com.epam.airline.model.entity.Airport;
import com.epam.airline.model.reader.AirlineReader;

public class AirportFiller {
    public void fillAirportWithAirlines(String path) {
        AirlineReader reader = new AirlineReader();
        Airport.getAirport().addToAirlines(reader.readAirlinesFromFile(path));
    }
}
