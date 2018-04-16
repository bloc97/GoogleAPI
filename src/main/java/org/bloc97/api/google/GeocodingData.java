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
public class GeocodingData {
    
    private final String address;
    private final double latitude, longitude;

    //Container class for GeocodingData
    protected GeocodingData(String address, double latitude, double longitude) {
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * Gets an human-readable address
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @return latitude
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * @return longitude
     */
    public double getLongitude() {
        return longitude;
    }
    
    @Override
    public String toString() {
        
        return "(" + getAddress() + ") " + getLatitude() + "," + getLongitude();
    }
}
