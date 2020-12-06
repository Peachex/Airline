package com.epam.airline.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AirlineValidator {
    private static final String DESTINATION_REGEX = "[a-zA-Z]{2,20}";
    private static final String TICKET_COST_REGEX = "[0-9]{1,6}.[0-9]{0,2}";
    private static final String[] AVAILABLE_DAY_OF_WEEKS = {"MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY",
            "FRIDAY", "SATURDAY", "SUNDAY"};

    public static boolean isDestinationValid(String destination) {
        boolean result;
        Pattern pattern = Pattern.compile(DESTINATION_REGEX);
        Matcher matcher = pattern.matcher(destination);
        result = matcher.matches();
        return result;
    }

    public static boolean isTicketCostValid(String ticketCost) {
        boolean result;
        Pattern pattern = Pattern.compile(TICKET_COST_REGEX);
        Matcher matcher = pattern.matcher(ticketCost);
        result = matcher.matches();
        return result;
    }

    public static boolean isDayOfWeekValid(String dayOfWeek) {
        boolean result = false;
        int count = 0;
        while (count < AVAILABLE_DAY_OF_WEEKS.length) {
            if (dayOfWeek.toUpperCase().equals(AVAILABLE_DAY_OF_WEEKS[count++])) {
                result = true;
                break;
            }
        }
        return result;
    }
}
