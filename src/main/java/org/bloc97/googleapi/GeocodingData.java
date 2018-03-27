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
public class GeocodingData {
    
    private final String address;
    private final double latitude, longitude;

    public GeocodingData(String address, double latitude, double longitude) {
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getAddress() {
        return address;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
    
    @Override
    public String toString() {
        
        return "(" + getAddress() + ") " + getLatitude() + "," + getLongitude();
    }
}
