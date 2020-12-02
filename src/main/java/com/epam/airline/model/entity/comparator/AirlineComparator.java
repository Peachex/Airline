package com.epam.airline.model.entity.comparator;

import com.epam.airline.model.entity.Airline;

import java.util.Comparator;

public enum AirlineComparator implements Comparator<Airline> {
    DESTINATION {
        @Override
        public int compare(Airline o1, Airline o2) {
            return o1.getDestination().compareToIgnoreCase(o2.getDestination());
        }
    },

    DAY_OF_WEEK {
        @Override
        public int compare(Airline o1, Airline o2) {
            return o1.getDayOfWeek().compareTo(o2.getDayOfWeek());
        }
    }
}
