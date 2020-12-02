package com.epam.airline.model.entity;

import com.epam.airline.util.AirlineIdGenerator;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.time.DayOfWeek;

public class Airline {
    private final long airlineId;
    private String destination;
    private String flightNumber;
    private String planeType;
    private LocalTime departureTime;
    private DayOfWeek dayOfWeek;
    private BigDecimal ticketCost;

    public Airline(String destination, String flightNumber, String planeType, LocalTime departureTime,
                   DayOfWeek dayOfWeek, BigDecimal ticketCost) {
        this.airlineId = AirlineIdGenerator.getIncreasedIdValue();
        this.destination = destination;
        this.flightNumber = flightNumber;
        this.planeType = planeType;
        this.departureTime = departureTime;
        this.dayOfWeek = dayOfWeek;
        this.ticketCost = ticketCost;
    }

    public long getAirlineId() {
        return this.airlineId;
    }

    public String getDestination() {
        return this.destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getFlightNumber() {
        return this.flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getPlaneType() {
        return this.planeType;
    }

    public void setPlaneType(String planeType) {
        this.planeType = planeType;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public BigDecimal getTicketCost() {
        return this.ticketCost;
    }

    public void setTicketCost(BigDecimal ticketCost) {
        this.ticketCost = ticketCost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Airline airline = (Airline) o;
        if (!ticketCost.equals(airline.ticketCost)) {
            return false;
        }
        if (dayOfWeek != airline.dayOfWeek) {
            return false;
        }
        return (destination.equals(airline.destination) &&
                flightNumber.equals(airline.flightNumber) &&
                planeType.equals(airline.planeType) &&
                departureTime.equals(airline.departureTime));
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + (ticketCost != null ? ticketCost.hashCode() : 0);
        result = 31 * result + (dayOfWeek != null ? dayOfWeek.hashCode() : 0);
        result = 31 * result + (destination != null ? destination.hashCode() : 0);
        result = 31 * result + (flightNumber != null ? flightNumber.hashCode() : 0);
        result = 31 * result + (planeType != null ? planeType.hashCode() : 0);
        result = 31 * result + (departureTime != null ? departureTime.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("id = ").append(airlineId);
        sb.append("\ndestination = ").append(destination);
        sb.append("\nflightNumber = ").append(flightNumber);
        sb.append("\nplaneType = ").append(planeType);
        sb.append("\ndepartureTime = ").append(departureTime);
        sb.append("\ndayOfWeek = ").append(dayOfWeek);
        sb.append("\nticketCost = ").append(ticketCost);
        sb.append("$\n");
        return sb.toString();
    }
}
