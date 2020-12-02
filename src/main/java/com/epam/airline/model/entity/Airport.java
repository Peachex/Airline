package com.epam.airline.model.entity;

import java.util.ArrayList;
import java.util.List;

public class Airport {
    private static final Airport airport = new Airport();
    private List<Airline> airlines = new ArrayList<>();

    private Airport() {
    }

    public static Airport getAirport() {
        return airport;
    }

    public void clearAllAirlines() {
        this.airlines = new ArrayList<>();
    }

    public Airline getAirline(int index) {
        return airlines.get(index);
    }

    public void addToAirlines(Airline airline) {
        this.airlines.add(airline);
    }

    public void addToAirlines(List<Airline> airlines) {
        this.airlines.addAll(airlines);
    }

    public int getSize() {
        return this.airlines.size();
    }

    public void deleteAirline(Airline airline) {
        this.airlines.remove(airline);
    }

    public boolean contains(Airline airline) {
        return this.airlines.contains(airline);
    }

    public void updateAirline(int index, Airline airline) {
        this.airlines.set(index, airline);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Airport that = (Airport) o;
        int length = this.airlines.size();
        if (that.airlines.size() != length) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            if (!that.airlines.get(i).equals(this.airlines.get(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        if (this.airlines == null) {
            return 0;
        }
        int result = 1;
        for (Airline airline : airlines) {
            result = 31 * result + (airline == null ? 0 : airline.hashCode());
        }
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Airport{");
        sb.append("airlines=").append(airlines);
        sb.append('}');
        return sb.toString();
    }
}
