/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bloc97.api.darksky.data;

import org.bloc97.api.darksky.data.ForecastDataPointAll;
import java.util.Date;

/**
 *
 * @author bowen
 */
public class ForecastDataPoint {
    private final double cloudCover, dewPoint, humidity;
    private final String icon; //clear-day, clear-night, rain, snow, sleet, wind, fog, cloudy, partly-cloudy-day, partly-cloudy-night, etc. see https://darksky.net/dev/docs#response-format
    private final double ozone;
    private final double precipIntensity;
    private final double precipProbability;
    private final String precipType; //rain, snow, sleet, etc. might be undefined
    
    private final double pressure;
    private final String summary;
    
    private final Date time;
    private final double uvIndex;
    private final double visibility;
    private final double windBearing, windGust, windSpeed;

    public ForecastDataPoint(ForecastDataPointAll all) {
        this.cloudCover = all.cloudCover;
        this.dewPoint = all.dewPoint;
        this.humidity = all.humidity;
        this.icon = all.icon;
        this.ozone = all.ozone;
        this.precipIntensity = all.precipIntensity;
        this.precipProbability = all.precipProbability;
        this.precipType = all.precipType;
        this.pressure = all.pressure;
        this.summary = all.summary;
        this.time = all.time;
        this.uvIndex = all.uvIndex;
        this.visibility = all.visibility;
        this.windBearing = all.windBearing;
        this.windGust = all.windGust;
        this.windSpeed = all.windSpeed;
    }
    
    public ForecastDataPoint(double cloudCover, double dewPoint, double humidity, String icon, double ozone, double precipIntensity, double precipProbability, String precipType, double pressure, String summary, Date time, double uvIndex, double visibility, double windBearing, double windGust, double windSpeed) {
        this.cloudCover = cloudCover;
        this.dewPoint = dewPoint;
        this.humidity = humidity;
        this.icon = icon;
        this.ozone = ozone;
        this.precipIntensity = precipIntensity;
        this.precipProbability = precipProbability;
        this.precipType = precipType;
        this.pressure = pressure;
        this.summary = summary;
        this.time = time;
        this.uvIndex = uvIndex;
        this.visibility = visibility;
        this.windBearing = windBearing;
        this.windGust = windGust;
        this.windSpeed = windSpeed;
    }

    public double getCloudCover() {
        return cloudCover;
    }

    public double getDewPoint() {
        return dewPoint;
    }

    public double getHumidity() {
        return humidity;
    }

    public String getIcon() {
        return icon;
    }

    public double getOzone() {
        return ozone;
    }

    public double getPrecipIntensity() {
        return precipIntensity;
    }

    public double getPrecipProbability() {
        return precipProbability;
    }

    public String getPrecipType() {
        return precipType;
    }

    public double getPressure() {
        return pressure;
    }

    public String getSummary() {
        return summary;
    }

    public Date getTime() {
        return time;
    }

    public double getUvIndex() {
        return uvIndex;
    }

    public double getVisibility() {
        return visibility;
    }

    public double getWindBearing() {
        return windBearing;
    }

    public double getWindGust() {
        return windGust;
    }

    public double getWindSpeed() {
        return windSpeed;
    }
    
    
    
}
