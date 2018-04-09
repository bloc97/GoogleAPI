/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bloc97.api.darksky.data;

import java.util.Date;

/**
 *
 * @author bowen
 */
public class ForecastDataPointAll {
    protected final double cloudCover, dewPoint, humidity;
    protected final String icon; //clear-day, clear-night, rain, snow, sleet, wind, fog, cloudy, partly-cloudy-day, partly-cloudy-night, etc. see https://darksky.net/dev/docs#response-format
    protected final double ozone;
    protected final double precipIntensity;
    protected final double precipProbability;
    protected final String precipType; //rain, snow, sleet, etc. might be undefined
    
    protected final double pressure;
    protected final String summary;
    
    protected final Date time;
    protected final double uvIndex;
    protected final double visibility;
    protected final double windBearing, windGust, windSpeed;
    
    protected final double nearestStormBearing, nearestStormDistance;
    protected final double apparentTemperature;
    protected final double temperature;
    protected final double precipAccumulation;
    
    
    protected final double apparentTemperatureHigh, apparentTemperatureLow;
    protected final Date apparentTemperatureHighTime, apparentTemperatureLowTime;
    protected final double moonPhase;
    protected final double precipIntensityMax;
    protected final Date precipIntensityMaxTime;
    
    protected final double sunriseTime, sunsetTime;
    protected final double temperatureHigh, temperatureLow;
    protected final Date temperatureHighTime, temperatureLowTime;
    protected final Date uvIndexTime;

    protected ForecastDataPointAll(double cloudCover, double dewPoint, double humidity, String icon, double ozone, double precipIntensity, double precipProbability, String precipType, double pressure, String summary, Date time, double uvIndex, double visibility, double windBearing, double windGust, double windSpeed, double nearestStormBearing, double nearestStormDistance, double apparentTemperature, double temperature, double precipAccumulation, double apparentTemperatureHigh, double apparentTemperatureLow, Date apparentTemperatureHighTime, Date apparentTemperatureLowTime, double moonPhase, double precipIntensityMax, Date precipIntensityMaxTime, double sunriseTime, double sunsetTime, double temperatureHigh, double temperatureLow, Date temperatureHighTime, Date temperatureLowTime, Date uvIndexTime) {
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
        this.nearestStormBearing = nearestStormBearing;
        this.nearestStormDistance = nearestStormDistance;
        this.apparentTemperature = apparentTemperature;
        this.temperature = temperature;
        this.precipAccumulation = precipAccumulation;
        this.apparentTemperatureHigh = apparentTemperatureHigh;
        this.apparentTemperatureLow = apparentTemperatureLow;
        this.apparentTemperatureHighTime = apparentTemperatureHighTime;
        this.apparentTemperatureLowTime = apparentTemperatureLowTime;
        this.moonPhase = moonPhase;
        this.precipIntensityMax = precipIntensityMax;
        this.precipIntensityMaxTime = precipIntensityMaxTime;
        this.sunriseTime = sunriseTime;
        this.sunsetTime = sunsetTime;
        this.temperatureHigh = temperatureHigh;
        this.temperatureLow = temperatureLow;
        this.temperatureHighTime = temperatureHighTime;
        this.temperatureLowTime = temperatureLowTime;
        this.uvIndexTime = uvIndexTime;
    }
    
}
