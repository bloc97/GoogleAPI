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
public class DirectionsRequestBuilder {
    
    private String origin = "null";
    private String destination = "null";
    private String key = "0";
    
    private boolean isDepartureTime = true;
    private Date time = null;
    
    private DirectionsRequest.Mode mode = DirectionsRequest.Mode.DEFAULT;
    private DirectionsRequest.TrafficModel trafficModel = DirectionsRequest.TrafficModel.DEFAULT;
    
    /**
     * Sets the departure location.
     * @param address Human readable address or string formatted latitude/longitude.<br>
     * eg.<br><code>
     * - "24 Sussex Drive, Ottawa, ON"<br>
     * - "41.43206,-81.38992"</code>
     * @return self for builder chaining.
     */
    public DirectionsRequestBuilder setOrigin(String address) {
        this.origin = address;
        return this;
    }
    /**
     * Sets the arrival location.
     * @param address Human readable address or string formatted latitude/longitude.<br>
     * eg.<br><code>
     * - "24 Sussex Drive, Ottawa, ON"<br>
     * - "41.43206,-81.38992"</code>
     * @return self for builder chaining.
     */
    public DirectionsRequestBuilder setDestination(String address) {
        this.destination = address;
        return this;
    }

    /**
     * @param key String representing the Google API key
     * @return self for builder chaining.
     */
    public DirectionsRequestBuilder setApiKey(String key) {
        this.key = key;
        return this;
    }
    
    /**
     * @param date Requested departure time, must be either null, the current time or a future time.<br>
     * Passing a null variable is considered to be always current time.<br>
     * Note that this overrides setArrivalTime, as it is only allowed to have a single time target.
     * @return self for builder chaining.
     */
    public DirectionsRequestBuilder setDepartureTime(Date date) {
        isDepartureTime = true;
        this.time = date;
        return this;
    }
    
    /**
     *
     * @param date Requested arrival time, must be in the future.<br>
     * Note that this overrides setDepartureTime, as it is only allowed to have a single time target.
     * @return self for builder chaining.
     */
    public DirectionsRequestBuilder setArrivalTime(Date date) {
        isDepartureTime = false;
        this.time = date;
        return this;
    }
    
    /**
     *
     * @param mode Transit mode
     * @return self for builder chaining.
     */
    public DirectionsRequestBuilder setMode(DirectionsRequest.Mode mode) {
        this.mode = mode;
        return this;
    }
    
    /**
     *
     * @param trafficModel
     * @return self for builder chaining.
     */
    public DirectionsRequestBuilder setTrafficModel(DirectionsRequest.TrafficModel trafficModel) {
        this.trafficModel = trafficModel;
        return this;
    }
    
    DirectionsRequestBuilder set(DirectionsRequest request) {
        setOrigin(request.getOrigin());
        setDestination(request.getDestination());
        setApiKey(request.getApiKey());
        if (request.isDepartureTime()) {
            setDepartureTime(request.getTime());
        } else {
            setArrivalTime(request.getTime());
        }
        setMode(request.getMode());
        setTrafficModel(request.getTrafficModel());
        return this;
    }
    
    /**
     * @return DirectionsRequest
     */
    public DirectionsRequest build() {
        if (key.equals("0")) {
            throw new IllegalStateException("Must include the api key in the request!");
        }
        return new DirectionsRequest(origin, destination, key, isDepartureTime, time, mode, trafficModel);
    }
}
