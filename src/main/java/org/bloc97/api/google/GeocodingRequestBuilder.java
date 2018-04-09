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
public class GeocodingRequestBuilder {
    
    private String location = "null";
    private String key = "0";

    public GeocodingRequestBuilder setKey(String key) {
        this.key = key;
        return this;
    }
    
    public GeocodingRequestBuilder setLocation(String location) {
        this.location = location;
        return this;
    }
    
    public GeocodingRequest build() {
        return new GeocodingRequest(location, key);
    }
    
}
