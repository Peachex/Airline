package com.epam.airline.model.dao;

import com.epam.airline.exception.DaoException;
import com.epam.airline.model.entity.Airline;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Optional;

public interface AirlineDao {
    boolean exists(Airline airline) throws DaoException;

    void add(Airline airline) throws DaoException;

    void add(List<Airline> airlines) throws DaoException;

    void delete(Airline airline) throws DaoException;

    void clearAllAirlines();

    void updateAirline(long id, Airline airline) throws DaoException;

    Optional<Airline> findByDestination(String destination);

    Optional<Airline> findByDayOfWeek(DayOfWeek dayOfWeek);

    Optional<Airline> findByDestinationAndByDayOfWeek(String destination, DayOfWeek dayOfWeek);

    List<Airline> findAll();
}
