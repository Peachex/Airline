package com.epam.airline.util;

public class AirlineIdGenerator {
    private static long currentIdValue = 0;

    public static long getIncreasedIdValue() {
        return currentIdValue++;
    }
}
