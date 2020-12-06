package com.epam.airline.model.service;

import com.epam.airline.exception.ServiceException;
import com.epam.airline.model.filler.AirportFiller;
import com.epam.airline.model.dao.AirlineDao;
import com.epam.airline.model.dao.impl.AirlineDaoImpl;
import com.epam.airline.model.entity.Airline;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

import com.epam.airline.model.entity.Airport;
import com.epam.airline.model.service.impl.AirportServiceImpl;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class AirportServiceTest {

    private List<Airline> airlinesList;
    private AirlineDao dao = new AirlineDaoImpl();
    private AirportService service = new AirportServiceImpl(dao);
    private AirportFiller filler = new AirportFiller();

    @BeforeMethod
    public void initializeAirlines() {
        String path = "data\\airlines.txt";
        dao.clearAllAirlines();
        filler.fillAirportWithAirlines(path);
        airlinesList = new ArrayList<>();
        airlinesList.add(new Airline("Minsk", "4", "Jumbo Passenger Jet",
                LocalTime.of(10, 11, 23), DayOfWeek.SUNDAY,
                new BigDecimal("19.99")));

        airlinesList.add(new Airline("Beijing", "2", "Mid Size Passenger Jet",
                LocalTime.of(5, 25, 1), DayOfWeek.FRIDAY,
                new BigDecimal("10.00")));

        airlinesList.add(new Airline("Denver", "1", "Light Passenger Jet",
                LocalTime.of(14, 32, 56), DayOfWeek.MONDAY,
                new BigDecimal("9.39")));

        airlinesList.add(new Airline("Minsk", "3", "Passenger Turbo Props",
                LocalTime.of(19, 19, 19), DayOfWeek.FRIDAY,
                new BigDecimal("15.55")));

        airlinesList.add(new Airline("Denver", "5", "Cargo",
                LocalTime.of(23, 59, 59), DayOfWeek.WEDNESDAY,
                new BigDecimal("34.23")));

        airlinesList.add(new Airline("Barcelona", "7", "Jumbo Passenger Jet",
                LocalTime.of(9, 54, 22), DayOfWeek.TUESDAY,
                new BigDecimal("15.99")));

        airlinesList.add(new Airline("Beijing", "6", "Mid Size Passenger Jet",
                LocalTime.of(3, 5, 15), DayOfWeek.SATURDAY,
                new BigDecimal("40.00")));

        airlinesList.add(new Airline("Denver", "8", "Light Passenger Jet",
                LocalTime.of(20, 30, 50), DayOfWeek.MONDAY,
                new BigDecimal("19.39")));

        airlinesList.add(new Airline("Minsk", "9", "Passenger Turbo Props",
                LocalTime.of(17, 01, 02), DayOfWeek.THURSDAY,
                new BigDecimal("10.55")));

        airlinesList.add(new Airline("Denver", "10", "Cargo",
                LocalTime.of(23, 59, 59), DayOfWeek.WEDNESDAY,
                new BigDecimal("24.23")));
    }

    @Test
    public void addAirline() throws ServiceException {
        List<Airline> expected = service.findAll();
        Airline airline = new Airline("Ukraine", "11", "Jet",
                LocalTime.of(12, 14, 10), DayOfWeek.THURSDAY, new BigDecimal("29.32"));

        expected.add(airline);
        service.addAirline(airline);
        List<Airline> actual = service.findAll();
        assertEquals(actual, expected);
    }

    @Test
    public void addAirlines() throws ServiceException {
        List<Airline> expected = service.findAll();
        Airline airline = new Airline("Ukraine", "11", "Jet",
                LocalTime.of(12, 14, 10), DayOfWeek.THURSDAY, new BigDecimal("29.32"));

        List<Airline> airlines = new ArrayList<>();
        airlines.add(airline);
        expected.addAll(airlines);
        service.addAirlines(airlines);
        List<Airline> actual = service.findAll();
        assertEquals(actual, expected);
    }

    @Test
    public void deleteAirline() throws ServiceException {
        List<Airline> expected = service.findAll();
        expected.remove(airlinesList.get(0));
        service.deleteAirline(airlinesList.get(0));
        List<Airline> actual = service.findAll();
        assertEquals(actual, expected);
    }

    @Test
    public void updateAirline() throws ServiceException {
        List<Airline> expected = service.findAll();
        Airline airline = new Airline("Ukraine", "11", "Jet",
                LocalTime.of(12, 14, 10), DayOfWeek.THURSDAY, new BigDecimal("29.32"));

        expected.set(0, airline);
        service.updateAirline(Airport.getAirport().getAirline(0).getAirlineId(), airline);
        List<Airline> actual = service.findAll();
        assertEquals(actual, expected);
    }

    @Test
    public void findByDestination() throws ServiceException {
        Airline expected = airlinesList.get(0);
        Airline actual = service.findByDestination("Minsk");
        assertEquals(actual, expected);
    }

    @Test
    public void findByDayOfWeek() throws ServiceException {
        Airline expected = airlinesList.get(0);
        Airline actual = service.findByDayOfWeek(DayOfWeek.SUNDAY);
        assertEquals(actual, expected);
    }

    @Test
    public void findByDestinationAndDayOfWeek() throws ServiceException {
        Airline expected = airlinesList.get(0);
        Airline actual = service.findByDestinationAndDayOfWeek("Minsk", DayOfWeek.SUNDAY);
        assertEquals(actual, expected);
    }

    @Test
    public void findAll() {
        List<Airline> expected = new ArrayList(airlinesList);
        List<Airline> actual = service.findAll();
        assertEquals(actual, expected);
    }

    @Test
    public void sortByDestination() {
        List<Airline> expected = new ArrayList<>();
        expected.add(airlinesList.get(5));
        expected.add(airlinesList.get(1));
        expected.add(airlinesList.get(6));
        expected.add(airlinesList.get(2));
        expected.add(airlinesList.get(4));
        expected.add(airlinesList.get(7));
        expected.add(airlinesList.get(9));
        expected.add(airlinesList.get(0));
        expected.add(airlinesList.get(3));
        expected.add(airlinesList.get(8));
        List<Airline> actual = service.sortByDestination(false);
        assertEquals(actual, expected);
    }

    @Test
    public void sortByDayOfWeek() {
        List<Airline> expected = new ArrayList<>();
        expected.add(airlinesList.get(2));
        expected.add(airlinesList.get(7));
        expected.add(airlinesList.get(5));
        expected.add(airlinesList.get(4));
        expected.add(airlinesList.get(9));
        expected.add(airlinesList.get(8));
        expected.add(airlinesList.get(1));
        expected.add(airlinesList.get(3));
        expected.add(airlinesList.get(6));
        expected.add(airlinesList.get(0));
        List<Airline> actual = service.sortByDayOfWeek(false);
        assertEquals(actual, expected);
    }

    @Test
    public void sortByDestinationAndDayOfWeek() {
        List<Airline> expected = new ArrayList<>();
        expected.add(airlinesList.get(5));
        expected.add(airlinesList.get(1));
        expected.add(airlinesList.get(6));
        expected.add(airlinesList.get(2));
        expected.add(airlinesList.get(4));
        expected.add(airlinesList.get(7));
        expected.add(airlinesList.get(9));
        expected.add(airlinesList.get(0));
        expected.add(airlinesList.get(3));
        expected.add(airlinesList.get(8));
        List<Airline> actual = service.sortByDestination(false);
        assertEquals(actual, expected);
    }
}
