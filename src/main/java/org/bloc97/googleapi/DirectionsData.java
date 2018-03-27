/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bloc97.googleapi;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author bowen
 */
public class DirectionsData {
    private final String start, end;
    private final int distance, time;
    private final Date departure, arrival;

    public DirectionsData(String startAddress, String endAddress, int distance, int time, int departure, int arrival) {
        this.distance = distance;
        this.time = time;
        this.start = startAddress;
        this.end = endAddress;
        this.departure = new Date(departure * 1000L);
        this.arrival = new Date(arrival * 1000L);
    }

    /**
     * @return Directions estimated time in seconds
     */
    public int getTime() {
        return time;
    }
    /**
     * @return Directions estimated time in seconds
     */
    public int getTimeSeconds() {
        return time%60;
    }
    /**
     * @return Directions estimated time in seconds
     */
    public int getTimeMinutes() {
        return time/60%60;
    }
    /**
     * @return Directions estimated time in seconds
     */
    public int getTimeHours() {
        return time/60/60;
    }

    /**
     * @return Directions distance in meters
     */
    public int getDistance() {
        return distance;
    }

    public String getOrigin() {
        return start;
    }

    public String getDestination() {
        return end;
    }

    public Date getDepartureTime() {
        return departure;
    }

    public Date getArrivalTime() {
        return arrival;
    }
    
    @Override
    public String toString() {
        DateFormat format = new SimpleDateFormat();
        
        return "(" + start + ") -> (" + end + ")" + "\n" +
                "Distance: " + distance + " m" + "\n" +
                "Duration: " + getTimeHours() + " hours, " + getTimeMinutes() + " minutes, " + getTimeSeconds() + " seconds" + "\n" +
                "Departure: " + format.format(getDepartureTime()) + "\n" +
                "Arrival: " + format.format(getArrivalTime());
    }
    
    
}
