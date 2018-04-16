/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bloc97.api.darksky;

import java.util.Date;
import java.util.List;

/**
 *
 * @author bowen
 */
public class ForecastRequest {
    private final double longitude, latitude;
    private final String key;
    private final Date time;
    private final List<DataGroup> excludes;
    private final boolean isExtendHourly;
    private final Language language;
    private final Units units;
    
    //Container class for forecast request
    protected ForecastRequest(double longitude, double latitude, String key, List<DataGroup> excludes, Date time, boolean isExtendHourly, Language language, Units units) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.key = key;
        this.excludes = excludes;
        this.time = time;
        this.isExtendHourly = isExtendHourly;
        this.language = language;
        this.units = units;
    }
    
    /**
     * Creates a builder for ForecastRequest
     * @return a ForecastRequestBuilder
     */
    public static ForecastRequestBuilder builder() {
        return new ForecastRequestBuilder();
    }
    
    public enum DataGroup {
        CURRENTLY, MINUTELY, HOURLY, DAILY, ALERTS, FLAGS;
        @Override
        public String toString() {
            return super.toString().toLowerCase();
        }
    }
    public enum Language {
        DEFAULT, EN, FR;
        @Override
        public String toString() {
            return super.toString().toLowerCase();
        }
    }
    
    public enum Units {
        DEFAULT, AUTO, CA, US, SI;
        @Override
        public String toString() {
            return super.toString().toLowerCase();
        }
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public String getKey() {
        return key;
    }

    public List<DataGroup> getExcludes() {
        return excludes;
    }

    public Date getTime() {
        return time;
    }

    public boolean isExtendHourly() {
        return isExtendHourly;
    }

    public Language getLanguage() {
        return language;
    }

    public Units getUnits() {
        return units;
    }
    
    
}
