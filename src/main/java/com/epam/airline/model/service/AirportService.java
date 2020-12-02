package com.epam.airline.model.service;

import com.epam.airline.exception.ServiceException;
import com.epam.airline.model.entity.Airline;

import java.time.DayOfWeek;
import java.util.List;

public interface AirportService {
    void addAirline(Airline airline) throws ServiceException;

    void addAirlines(List<Airline> airlines) throws ServiceException;

    void deleteAirline(Airline airline) throws ServiceException;

    void updateAirline(long id, Airline airline) throws ServiceException;

    Airline findByDestination(String destination) throws ServiceException;

    Airline findByDayOfWeek(DayOfWeek dayOfWeek) throws ServiceException;

    Airline findByDestinationAndDayOfWeek(String destination, DayOfWeek dayOfWeek) throws ServiceException;

    List<Airline> findAll();

    List<Airline> sortByDestination(boolean reverse);

    List<Airline> sortByDayOfWeek(boolean reverse);

    List<Airline> sortByDestinationAndDayOfWeek(boolean reverse);
}
