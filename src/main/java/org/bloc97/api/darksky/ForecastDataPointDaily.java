/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bloc97.api.darksky;

import java.util.Date;

/**
 *
 * @author bowen
 */
public class ForecastDataPointDaily extends ForecastDataPoint {
    private final double apparentTemperatureHigh, apparentTemperatureLow;
    private final Date apparentTemperatureHighTime, apparentTemperatureLowTime;
    private final double moonPhase;
    private final double precipAccumulation;
    private final double precipIntensityMax;
    private final Date precipIntensityMaxTime;
    
    private final double sunriseTime, sunsetTime;
    private final double temperatureHigh, temperatureLow;
    private final Date temperatureHighTime, temperatureLowTime;
    private final Date uvIndexTime;
    private final double temperature;

    public ForecastDataPointDaily(ForecastDataPointAll all) {
        super(all);
        this.apparentTemperatureHigh = all.apparentTemperatureHigh;
        this.apparentTemperatureLow = all.apparentTemperatureLow;
        this.apparentTemperatureHighTime = all.apparentTemperatureHighTime;
        this.apparentTemperatureLowTime = all.apparentTemperatureLowTime;
        this.moonPhase = all.moonPhase;
        this.precipAccumulation = all.precipAccumulation;
        this.precipIntensityMax = all.precipIntensityMax;
        this.precipIntensityMaxTime = all.precipIntensityMaxTime;
        this.sunriseTime = all.sunriseTime;
        this.sunsetTime = all.sunsetTime;
        this.temperatureHigh = all.temperatureHigh;
        this.temperatureLow = all.temperatureLow;
        this.temperatureHighTime = all.temperatureHighTime;
        this.temperatureLowTime = all.temperatureLowTime;
        this.uvIndexTime = all.uvIndexTime;
        this.temperature = all.temperature;
    }
    
    public ForecastDataPointDaily(double apparentTemperatureHigh, double apparentTemperatureLow, Date apparentTemperatureHighTime, Date apparentTemperatureLowTime, double moonPhase, double precipAccumulation, double precipIntensityMax, Date precipIntensityMaxTime, double sunriseTime, double sunsetTime, double temperatureHigh, double temperatureLow, Date temperatureHighTime, Date temperatureLowTime, Date uvIndexTime, double temperature, double cloudCover, double dewPoint, double humidity, String icon, double ozone, double precipIntensity, double precipProbability, String precipType, double pressure, String summary, Date time, double uvIndex, double visibility, double windBearing, double windGust, double windSpeed) {
        super(cloudCover, dewPoint, humidity, icon, ozone, precipIntensity, precipProbability, precipType, pressure, summary, time, uvIndex, visibility, windBearing, windGust, windSpeed);
        this.apparentTemperatureHigh = apparentTemperatureHigh;
        this.apparentTemperatureLow = apparentTemperatureLow;
        this.apparentTemperatureHighTime = apparentTemperatureHighTime;
        this.apparentTemperatureLowTime = apparentTemperatureLowTime;
        this.moonPhase = moonPhase;
        this.precipAccumulation = precipAccumulation;
        this.precipIntensityMax = precipIntensityMax;
        this.precipIntensityMaxTime = precipIntensityMaxTime;
        this.sunriseTime = sunriseTime;
        this.sunsetTime = sunsetTime;
        this.temperatureHigh = temperatureHigh;
        this.temperatureLow = temperatureLow;
        this.temperatureHighTime = temperatureHighTime;
        this.temperatureLowTime = temperatureLowTime;
        this.uvIndexTime = uvIndexTime;
        this.temperature = temperature;
    }

    public double getApparentTemperatureHigh() {
        return apparentTemperatureHigh;
    }

    public double getApparentTemperatureLow() {
        return apparentTemperatureLow;
    }

    public Date getApparentTemperatureHighTime() {
        return apparentTemperatureHighTime;
    }

    public Date getApparentTemperatureLowTime() {
        return apparentTemperatureLowTime;
    }

    public double getMoonPhase() {
        return moonPhase;
    }

    public double getPrecipAccumulation() {
        return precipAccumulation;
    }

    public double getPrecipIntensityMax() {
        return precipIntensityMax;
    }

    public Date getPrecipIntensityMaxTime() {
        return precipIntensityMaxTime;
    }

    public double getSunriseTime() {
        return sunriseTime;
    }

    public double getSunsetTime() {
        return sunsetTime;
    }

    public double getTemperatureHigh() {
        return temperatureHigh;
    }

    public double getTemperatureLow() {
        return temperatureLow;
    }

    public Date getTemperatureHighTime() {
        return temperatureHighTime;
    }

    public Date getTemperatureLowTime() {
        return temperatureLowTime;
    }

    public Date getUvIndexTime() {
        return uvIndexTime;
    }

    public double getTemperature() {
        return temperature;
    }

    
    
}
