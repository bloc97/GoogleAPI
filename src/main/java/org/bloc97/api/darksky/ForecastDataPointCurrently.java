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
public class ForecastDataPointCurrently extends ForecastDataPoint {
    private final double nearestStormBearing, nearestStormDistance;
    private final double apparentTemperature;
    private final double temperature;

    public ForecastDataPointCurrently(ForecastDataPointAll all) {
        super(all);
        this.nearestStormBearing = all.nearestStormBearing;
        this.nearestStormDistance = all.nearestStormDistance;
        this.apparentTemperature = all.apparentTemperature;
        this.temperature = all.temperature;
    }

    public ForecastDataPointCurrently(double nearestStormBearing, double nearestStormDistance, double apparentTemperature, double temperature, double cloudCover, double dewPoint, double humidity, String icon, double ozone, double precipIntensity, double precipProbability, String precipType, double pressure, String summary, Date time, double uvIndex, double visibility, double windBearing, double windGust, double windSpeed) {
        super(cloudCover, dewPoint, humidity, icon, ozone, precipIntensity, precipProbability, precipType, pressure, summary, time, uvIndex, visibility, windBearing, windGust, windSpeed);
        this.nearestStormBearing = nearestStormBearing;
        this.nearestStormDistance = nearestStormDistance;
        this.apparentTemperature = apparentTemperature;
        this.temperature = temperature;
    }

    public double getNearestStormBearing() {
        return nearestStormBearing;
    }

    public double getNearestStormDistance() {
        return nearestStormDistance;
    }

    public double getApparentTemperature() {
        return apparentTemperature;
    }

    public double getTemperature() {
        return temperature;
    }
    
}
