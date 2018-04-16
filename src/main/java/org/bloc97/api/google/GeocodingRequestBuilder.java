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

    /**
     * Sets the Google API key
     * @param key String representing the Google API key
     * @return self for builder chaining.
     */
    public GeocodingRequestBuilder setKey(String key) {
        this.key = key;
        return this;
    }
    
    /**
     * Sets location for geocoding.
     * @param location Human readable address or string formatted latitude/longitude.<br>
     * eg.<br><code>
     * - "24 Sussex Drive, Ottawa, ON"<br>
     * - "41.43206,-81.38992"</code>
     * @return self for builder chaining.
     */
    public GeocodingRequestBuilder setLocation(String location) {
        this.location = location;
        return this;
    }
    
    /**
     * Creates an GeocodingRequest
     * @return GeocodingRequest
     */
    public GeocodingRequest build() {
        if (key.equals("0")) {
            throw new IllegalStateException("Must include the api key in the request!");
        }
        return new GeocodingRequest(location, key);
    }
    
}
