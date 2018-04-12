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

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setExcludes(ForecastRequest.DataGroup... excludes) {
        if (excludes != null && excludes.length > 0) {
            this.excludes = Arrays.asList(excludes);
        } else {
            this.excludes = null;
        }
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public void setIsExtendHourly(boolean isExtendHourly) {
        this.isExtendHourly = isExtendHourly;
    }

    public void setLanguage(ForecastRequest.Language language) {
        this.language = language;
    }

    public void setUnits(ForecastRequest.Units units) {
        this.units = units;
    }
    
    public ForecastRequest build() {
        if (key.equals("0")) {
            throw new IllegalStateException("Must include the api key in the request!");
        }
        return new ForecastRequest(longitude, latitude, key, excludes, time, isExtendHourly, language, units);
    }
    
}
