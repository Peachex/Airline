package com.epam.airline.model.reader;

import com.epam.airline.model.entity.Airline;
import com.epam.airline.validator.AirlineValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

public class AirlineReader {
    private static final Logger logger = LogManager.getLogger();
    private static final int DESTINATION_FIELD_NUMBER = 0;
    private static final int FLIGHT_NUMBER_FIELD_NUMBER = 1;
    private static final int PLANE_TYPE_FIELD_NUMBER = 2;
    private static final int DEPARTURE_TIME_HOUR_FIELD_NUMBER = 3;
    private static final int DEPARTURE_TIME_MINUTE_FIELD_NUMBER = 4;
    private static final int DEPARTURE_TIME_SECOND_FIELD_NUMBER = 5;
    private static final int DAY_OF_WEEK_FIELD_NUMBER = 6;
    private static final int TICKET_COST_FIELD_NUMBER = 7;

    public List<Airline> readAirlinesFromFile(String path) {
        StringBuilder sb = new StringBuilder();
        try (java.io.FileReader fr = new java.io.FileReader(path); BufferedReader br = new BufferedReader(fr)) {
            String str;
            while ((str = br.readLine()) != null) {
                sb.append(str).append("\n");
            }
        } catch (IOException e) {
            logger.log(Level.ERROR, e);
        }
        String lineDelimiter = "\n";
        Pattern pattern = Pattern.compile(lineDelimiter);
        String[] airlines = pattern.split(sb);

        List<Airline> result = new ArrayList<>();

        for (String airline : airlines) {
            if (convertStringToAirline(airline).isPresent()) {
                result.add(convertStringToAirline(airline).get());
            }
        }
        return result;
    }

    private Optional<Airline> convertStringToAirline(String str) {
        Optional<Airline> result = Optional.empty();

        String elementDelimiter = ",\\s";
        Pattern pattern = Pattern.compile(elementDelimiter);
        String[] fields = pattern.split(str);

        String destination = null;
        if (AirlineValidator.isDestinationValid(fields[DESTINATION_FIELD_NUMBER])) {
            destination = fields[DESTINATION_FIELD_NUMBER];
        }

        String flightNumber = fields[FLIGHT_NUMBER_FIELD_NUMBER];
        String planeType = fields[PLANE_TYPE_FIELD_NUMBER];

        int hour = Integer.parseInt(fields[DEPARTURE_TIME_HOUR_FIELD_NUMBER]);
        int minute = Integer.parseInt(fields[DEPARTURE_TIME_MINUTE_FIELD_NUMBER]);
        int second = Integer.parseInt(fields[DEPARTURE_TIME_SECOND_FIELD_NUMBER]);
        LocalTime departureTime = LocalTime.of(hour, minute, second);

        String day = fields[DAY_OF_WEEK_FIELD_NUMBER];
        DayOfWeek dayOfWeek = null;
        if (AirlineValidator.isDayOfWeekValid(fields[DAY_OF_WEEK_FIELD_NUMBER])) {
            dayOfWeek = DayOfWeek.valueOf(day);
        }

        BigDecimal ticketCost = null;
        if (AirlineValidator.isTicketCostValid(fields[TICKET_COST_FIELD_NUMBER])) {
            ticketCost = new BigDecimal(fields[TICKET_COST_FIELD_NUMBER]);
        }

        return (destination == null && dayOfWeek == null && ticketCost == null) ?
                result : Optional.of(new Airline(destination, flightNumber, planeType, departureTime, dayOfWeek, ticketCost));
    }
}
