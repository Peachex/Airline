package com.epam.airline.model.service.impl;

import com.epam.airline.exception.DaoException;
import com.epam.airline.exception.ServiceException;
import com.epam.airline.model.dao.AirlineDao;
import com.epam.airline.model.entity.Airline;
import com.epam.airline.model.entity.comparator.AirlineComparator;
import com.epam.airline.model.service.AirportService;
import com.epam.airline.validator.AirlineValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.DayOfWeek;
import java.util.List;
import java.util.ArrayList;

public class AirportServiceImpl implements AirportService {
    private final static Logger logger = LogManager.getLogger();
    private final static String NEGATIVE_ID_ERROR_MESSAGE = "Negative id";
    private final static String NULL_OBJECT_ERROR_MESSAGE = "Object is null";
    private final static String WRONG_ARGUMENT_ERROR_MESSAGE = "Wrong destination";
    private final AirlineDao airlineDao;

    public AirportServiceImpl(AirlineDao airlineDao) {
        this.airlineDao = airlineDao;
    }

    @Override
    public void addAirline(Airline airline) throws ServiceException {
        if (airline == null) {
            throw new ServiceException(NULL_OBJECT_ERROR_MESSAGE);
        }
        try {
            airlineDao.add(airline);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e.getMessage());
        }
    }

    public void addAirlines(List<Airline> airlines) throws ServiceException {
        if (airlines == null) {
            throw new ServiceException(NULL_OBJECT_ERROR_MESSAGE);
        }
        try {
            airlineDao.add(airlines);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void deleteAirline(Airline airline) throws ServiceException {
        if (airline == null) {
            throw new ServiceException(NULL_OBJECT_ERROR_MESSAGE);
        }
        try {
            airlineDao.delete(airline);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void updateAirline(long id, Airline airline) throws ServiceException {
        if (id < 0) {
            throw new ServiceException(NEGATIVE_ID_ERROR_MESSAGE);
        }
        if (airline == null) {
            throw new ServiceException(NULL_OBJECT_ERROR_MESSAGE);
        }
        try {
            airlineDao.updateAirline(id, airline);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public Airline findByDestination(String destination) throws ServiceException {
        if (destination == null) {
            throw new ServiceException(NULL_OBJECT_ERROR_MESSAGE);
        }
        if (!AirlineValidator.isDestinationValid(destination)) {
            throw new ServiceException(WRONG_ARGUMENT_ERROR_MESSAGE);
        }
        return airlineDao.findByDestination(destination).get();
    }

    @Override
    public Airline findByDayOfWeek(DayOfWeek dayOfWeek) throws ServiceException {
        if (dayOfWeek == null) {
            throw new ServiceException(NULL_OBJECT_ERROR_MESSAGE);
        }
        return airlineDao.findByDayOfWeek(dayOfWeek).get();
    }

    @Override
    public Airline findByDestinationAndDayOfWeek(String destination, DayOfWeek dayOfWeek) throws ServiceException {
        if (destination == null || dayOfWeek == null) {
            throw new ServiceException(NULL_OBJECT_ERROR_MESSAGE);
        }
        if (!AirlineValidator.isDestinationValid(destination)) {
            throw new ServiceException(WRONG_ARGUMENT_ERROR_MESSAGE);
        }
        return airlineDao.findByDestinationAndByDayOfWeek(destination, dayOfWeek).get();
    }

    @Override
    public List<Airline> findAll() {
        return airlineDao.findAll();
    }

    @Override
    public List<Airline> sortByDestination(boolean reverse) {
        List<Airline> sortedList = new ArrayList<>(airlineDao.findAll());
        if (reverse) {
            sortedList.sort(AirlineComparator.DESTINATION.reversed());
        } else {
            sortedList.sort(AirlineComparator.DESTINATION);
        }
        return sortedList;
    }

    @Override
    public List<Airline> sortByDayOfWeek(boolean reverse) {
        List<Airline> sortedList = new ArrayList<>(airlineDao.findAll());
        if (reverse) {
            sortedList.sort(AirlineComparator.DAY_OF_WEEK.reversed());
        } else {
            sortedList.sort(AirlineComparator.DAY_OF_WEEK);
        }
        return sortedList;
    }

    @Override
    public List<Airline> sortByDestinationAndDayOfWeek(boolean reverse) {
        List<Airline> sortedList = new ArrayList<>(airlineDao.findAll());
        if (reverse) {
            sortedList.sort(AirlineComparator.DESTINATION.reversed().
                    thenComparing(AirlineComparator.DAY_OF_WEEK).reversed());
        } else {
            sortedList.sort(AirlineComparator.DESTINATION.thenComparing(AirlineComparator.DAY_OF_WEEK));
        }
        return sortedList;
    }
}
