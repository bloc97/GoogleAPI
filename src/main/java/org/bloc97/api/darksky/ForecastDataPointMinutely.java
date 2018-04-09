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
public class ForecastDataPointMinutely extends ForecastDataPoint {
    private final double apparentTemperature;

    public ForecastDataPointMinutely(ForecastDataPointAll all) {
        super(all);
        this.apparentTemperature = all.apparentTemperature;
    }
    
    public ForecastDataPointMinutely(double apparentTemperature, double cloudCover, double dewPoint, double humidity, String icon, double ozone, double precipIntensity, double precipProbability, String precipType, double pressure, String summary, Date time, double uvIndex, double visibility, double windBearing, double windGust, double windSpeed) {
        super(cloudCover, dewPoint, humidity, icon, ozone, precipIntensity, precipProbability, precipType, pressure, summary, time, uvIndex, visibility, windBearing, windGust, windSpeed);
        this.apparentTemperature = apparentTemperature;
    }

    public double getApparentTemperature() {
        return apparentTemperature;
    }
    
}
