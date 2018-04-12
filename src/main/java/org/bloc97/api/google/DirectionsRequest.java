/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bloc97.api.google;

import java.util.Date;

/**
 *
 * @author bowen
 */
public class DirectionsRequest {
    
    private final String origin, destination, key;
    private final boolean isDepartureTime;
    private final Date date;
    
    private final DirectionsRequest.Mode mode;
    private final DirectionsRequest.TrafficModel trafficModel;

    protected DirectionsRequest(String origin, String destination, String key, boolean isDepartureTime, Date date, Mode mode, TrafficModel trafficModel) {
        this.origin = origin;
        this.destination = destination;
        this.key = key;
        this.isDepartureTime = isDepartureTime;
        this.date = date;
        this.mode = mode;
        this.trafficModel = trafficModel;
    }
    
    public static final DirectionsRequestBuilder builder() {
        return new DirectionsRequestBuilder();
    }
    
    public enum Mode {
        DEFAULT, DRIVING, TRANSIT, WALKING, BICYCLING;
        @Override        
        public String toString() {
            return super.toString().toLowerCase();
        }
    }
    
    public enum TrafficModel {
        DEFAULT, BEST_GUESS, PESSIMISTIC, OPTIMISTIC;
        @Override        
        public String toString() {
            return super.toString().toLowerCase();
        }
    }

    /**
     * @return the origin
     */
    public String getOrigin() {
        return origin;
    }

    /**
     * @return the destination
     */
    public String getDestination() {
        return destination;
    }

    /**
     * @return the key
     */
    public String getApiKey() {
        return key;
    }

    /**
     * @return the isDepartureTime
     */
    public boolean isDepartureTime() {
        return isDepartureTime;
    }

    /**
     * @return the time
     */
    public Date getTime() {
        return date;
    }

    /**
     * @return the mode
     */
    public DirectionsRequest.Mode getMode() {
        return mode;
    }

    /**
     * @return the trafficModel
     */
    public DirectionsRequest.TrafficModel getTrafficModel() {
        return trafficModel;
    }
}
