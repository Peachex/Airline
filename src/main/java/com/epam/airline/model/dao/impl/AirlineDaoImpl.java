package com.epam.airline.model.dao.impl;

import com.epam.airline.exception.DaoException;
import com.epam.airline.model.dao.AirlineDao;
import com.epam.airline.model.entity.Airline;
import com.epam.airline.model.entity.Airport;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AirlineDaoImpl implements AirlineDao {
    private final static String REPEATING_AIRLINE_ERROR_MESSAGE = "Same airline in the list";
    private final static String MISSING_AIRLINE_ERROR_MESSAGE = "No such airline";

    private final Airport airport = Airport.getAirport();

    @Override
    public boolean exists(Airline airline) {
        return airport.contains(airline);
    }

    @Override
    public void add(Airline airline) throws DaoException {
        if (exists(airline)) {
            throw new DaoException(REPEATING_AIRLINE_ERROR_MESSAGE);
        }
        airport.addToAirlines(airline);
    }

    @Override
    public void add(List<Airline> airlines) throws DaoException {
        int count = 0;
        while (count < airlines.size()) {
            if (airport.contains(airlines.get(count++))) {
                throw new DaoException(REPEATING_AIRLINE_ERROR_MESSAGE);
            }
        }
        airport.addToAirlines(airlines);
    }

    @Override
    public void delete(Airline airline) throws DaoException {
        if (!exists(airline)) {
            throw new DaoException(MISSING_AIRLINE_ERROR_MESSAGE);
        }
        airport.deleteAirline(airline);
    }

    @Override
    public void clearAllAirlines() {
        airport.clearAllAirlines();
    }

    @Override
    public void updateAirline(long id, Airline airline) throws DaoException {
        boolean result = false;
        int i = 0;
        while (i < airport.getSize()) {
            if (airport.getAirline(i).getAirlineId() == id) {
                airport.updateAirline(i, airline);
                result = true;
                break;
            }
            i++;
        }
        if (!result) {
            throw new DaoException(MISSING_AIRLINE_ERROR_MESSAGE);
        }
    }

    @Override
    public Optional<Airline> findByDestination(String destination) {
        Optional<Airline> result = Optional.empty();
        int i = 0;
        while (i < airport.getSize()) {
            if (airport.getAirline(i).getDestination().equals(destination)) {
                result = Optional.of(airport.getAirline(i));
                break;
            }
            i++;
        }
        return result;
    }

    @Override
    public Optional<Airline> findByDayOfWeek(DayOfWeek dayOfWeek) {
        Optional<Airline> result = Optional.empty();
        int i = 0;
        while (i < airport.getSize()) {
            if (airport.getAirline(i).getDayOfWeek().equals(dayOfWeek)) {
                result = Optional.of(airport.getAirline(i));
                break;
            }
            i++;
        }
        return result;
    }

    @Override
    public Optional<Airline> findByDestinationAndByDayOfWeek(String destination, DayOfWeek dayOfWeek) {
        Optional<Airline> result = Optional.empty();
        int i = 0;
        while (i < airport.getSize()) {
            if (airport.getAirline(i).getDestination().equals(destination) &&
                    airport.getAirline(i).getDayOfWeek().equals(dayOfWeek)) {
                result = Optional.of(airport.getAirline(i));
                break;
            }
            i++;
        }
        return result;
    }

    @Override
    public List<Airline> findAll() {
        List<Airline> result = new ArrayList<>();
        int i = 0;
        while (i < airport.getSize()) {
            result.add(airport.getAirline(i++));
        }
        return result;
    }
}
