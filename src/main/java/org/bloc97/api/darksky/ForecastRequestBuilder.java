/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bloc97.api.darksky;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 *
 * @author bowen
 */
public class ForecastRequestBuilder {
    private double longitude = 0;
    private double latitude = 0;
    private String key = "0";
    private List<ForecastRequest.DataGroup> excludes = null;
    private Date time = null;
    private boolean isExtendHourly = false;
    private ForecastRequest.Language language = ForecastRequest.Language.DEFAULT;
    private ForecastRequest.Units units = ForecastRequest.Units.DEFAULT;

    /**
     * Sets the longitude, this is mandatory
     * @param longitude
     * @return self for builder chaining.
     */
    public ForecastRequestBuilder setLongitude(double longitude) {
        this.longitude = longitude;
        return this;
    }

    /**
     * Sets the latitude, this is mandatory
     * @param latitude
     * @return self for builder chaining.
     */
    public ForecastRequestBuilder setLatitude(double latitude) {
        this.latitude = latitude;
        return this;
    }

    /**
     * Sets the API key, this is mandatory
     * @param key
     * @return self for builder chaining.
     */
    public ForecastRequestBuilder setKey(String key) {
        this.key = key;
        return this;
    }

    /**
     * Sets data to exclude from the request
     * @param excludes
     * @return self for builder chaining.
     */
    public ForecastRequestBuilder setExcludes(ForecastRequest.DataGroup... excludes) {
        if (excludes != null && excludes.length > 0) {
            this.excludes = Arrays.asList(excludes);
        } else {
            this.excludes = null;
        }
        return this;
    }

    /**
     * Sets the begin time
     * @param time Time of forecast, null for current time
     * @return self for builder chaining.
     */
    public ForecastRequestBuilder setTime(Date time) {
        this.time = time;
        return this;
    }

    /**
     * Requests more hourly data
     * @param isExtendHourly
     * @return self for builder chaining.
     */
    public ForecastRequestBuilder setIsExtendHourly(boolean isExtendHourly) {
        this.isExtendHourly = isExtendHourly;
        return this;
    }

    /**
     * Sets the language
     * @param language
     * @return self for builder chaining.
     */
    public ForecastRequestBuilder setLanguage(ForecastRequest.Language language) {
        this.language = language;
        return this;
    }

    /**
     * Specifies the units type
     * @param units
     * @return self for builder chaining.
     */
    public ForecastRequestBuilder setUnits(ForecastRequest.Units units) {
        this.units = units;
        return this;
    }
    
    
    public ForecastRequest build() {
        if (key.equals("0")) {
            throw new IllegalStateException("Must include the api key in the request!");
        }
        return new ForecastRequest(longitude, latitude, key, excludes, time, isExtendHourly, language, units);
    }
    
}
