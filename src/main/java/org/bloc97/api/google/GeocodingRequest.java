/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bloc97.api.google;

/**
 *
 * @author bowen
 */
public class GeocodingRequest {
    private final String location, key;

    //Container class for Geocoding API requests
    protected GeocodingRequest(String location, String key) {
        this.location = location;
        this.key = key;
    }

    /**
     * Creates a builder for GeocodingRequest
     * @return a GeocodingRequestBuilder
     */
    public static final GeocodingRequestBuilder builder() {
        return new GeocodingRequestBuilder();
    }

    public String getKey() {
        return key;
    }

    public String getLocation() {
        return location;
    }    
    
}
