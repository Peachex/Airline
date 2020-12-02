package com.epam.airline.model.creator;

import static org.testng.Assert.assertEquals;

import com.epam.airline.model.entity.Airline;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class AirlineCreatorTest {
    @Test
    public void createAirlinesTest() {
        List<Airline> expected = new ArrayList<>();
        expected.add(new Airline("Minsk", "4", "Jumbo Passenger Jet",
                LocalTime.of(10, 11, 23), DayOfWeek.SUNDAY,
                new BigDecimal("19.99")));

        expected.add(new Airline("Beijing", "2", "Mid Size Passenger Jet",
                LocalTime.of(5, 25, 1), DayOfWeek.FRIDAY,
                new BigDecimal("10.00")));

        expected.add(new Airline("Denver", "1", "Light Passenger Jet",
                LocalTime.of(14, 32, 56), DayOfWeek.MONDAY,
                new BigDecimal("9.39")));

        expected.add(new Airline("Minsk", "3", "Passenger Turbo Props",
                LocalTime.of(19, 19, 19), DayOfWeek.FRIDAY,
                new BigDecimal("15.55")));
        expected.add(new Airline("Denver", "5", "Cargo",
                LocalTime.of(23, 59, 59), DayOfWeek.WEDNESDAY,
                new BigDecimal("34.23")));

        expected.add(new Airline("Barcelona", "7", "Jumbo Passenger Jet",
                LocalTime.of(9, 54, 22), DayOfWeek.TUESDAY,
                new BigDecimal("15.99")));

        expected.add(new Airline("Beijing", "6", "Mid Size Passenger Jet",
                LocalTime.of(3, 5, 15), DayOfWeek.SATURDAY,
                new BigDecimal("40.00")));

        expected.add(new Airline("Denver", "8", "Light Passenger Jet",
                LocalTime.of(20, 30, 50), DayOfWeek.MONDAY,
                new BigDecimal("19.39")));

        expected.add(new Airline("Minsk", "9", "Passenger Turbo Props",
                LocalTime.of(17, 01, 02), DayOfWeek.THURSDAY,
                new BigDecimal("10.55")));

        expected.add(new Airline("Denver", "10", "Cargo",
                LocalTime.of(23, 59, 59), DayOfWeek.WEDNESDAY,
                new BigDecimal("24.23")));

        AirlineCreator creator = new AirlineCreator();
        String path = "data\\airlines.txt";
        List<Airline> actual = creator.createAirlines(path);
        assertEquals(actual, expected);
    }
}
