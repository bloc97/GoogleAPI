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
public class ForecastDataPointHourly extends ForecastDataPoint {
    private final double precipAccumulation;
    private final double apparentTemperature;
    private final double temperature;

    public ForecastDataPointHourly(ForecastDataPointAll all) {
        super(all);
        this.precipAccumulation = all.precipAccumulation;
        this.apparentTemperature = all.apparentTemperature;
        this.temperature = all.temperature;
    }
    
    public ForecastDataPointHourly(double precipAccumulation, double apparentTemperature, double temperature, double cloudCover, double dewPoint, double humidity, String icon, double ozone, double precipIntensity, double precipProbability, String precipType, double pressure, String summary, Date time, double uvIndex, double visibility, double windBearing, double windGust, double windSpeed) {
        super(cloudCover, dewPoint, humidity, icon, ozone, precipIntensity, precipProbability, precipType, pressure, summary, time, uvIndex, visibility, windBearing, windGust, windSpeed);
        this.precipAccumulation = precipAccumulation;
        this.apparentTemperature = apparentTemperature;
        this.temperature = temperature;
    }

    public double getPrecipAccumulation() {
        return precipAccumulation;
    }

    public double getApparentTemperature() {
        return apparentTemperature;
    }

    public double getTemperature() {
        return temperature;
    }
    
    
}
