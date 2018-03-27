/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bloc97.googleapi;

/**
 *
 * @author bowen
 */
public class GeocodingRequest {
    private final String location, key;

    public GeocodingRequest(String location, String key) {
        this.location = location;
        this.key = key;
    }

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
