package com.epam.airline.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AirlineValidator {
    private static final String DESTINATION_REGEX = "[a-zA-Z]{2,20}";

    public static boolean isDestinationValid(String destination) {
        boolean result;
        Pattern pattern = Pattern.compile(DESTINATION_REGEX);
        Matcher matcher = pattern.matcher(destination);
        result = matcher.matches();
        return result;
    }
}
