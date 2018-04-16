/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bloc97.api.darksky;

import org.bloc97.api.darksky.data.ForecastDataAlert;
import org.bloc97.api.darksky.data.ForecastDataPointHourly;
import org.bloc97.api.darksky.data.ForecastDataPointCurrently;
import org.bloc97.api.darksky.data.ForecastDataPointDaily;
import org.bloc97.api.darksky.data.ForecastDataPointMinutely;
import java.util.List;

/**
 *
 * @author bowen
 */
public class ForecastData {
    
    private final double latitude, longitude;
    private final String timezone;
    
    private final ForecastDataPointCurrently currentlyData;
    
    private final String minutelySummary, minutelyIcon;
    private final List<ForecastDataPointMinutely> minutelyData;
    
    private final String hourlySummary, hourlyIcon;
    private final List<ForecastDataPointHourly> hourlyData;
    private final String dailySummary, dailyIcon;
    private final List<ForecastDataPointDaily> dailyData;
    
    private final List<ForecastDataAlert> alerts;
    
    private final boolean isDarkskyUnavailable;
    private final String unavailableInfo;
    private final List<String> sources;
    private final String units;

    //Container class for forecast data
    protected ForecastData(double latitude, double longitude, String timezone, ForecastDataPointCurrently currentlyData, String minutelySummary, String minutelyIcon, List<ForecastDataPointMinutely> minutelyData, String hourlySummary, String hourlyIcon, List<ForecastDataPointHourly> hourlyData, String dailySummary, String dailyIcon, List<ForecastDataPointDaily> dailyData, List<ForecastDataAlert> alerts, boolean isDarkskyUnavailable, String unavailableInfo, List<String> sources, String units) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.timezone = timezone;
        this.currentlyData = currentlyData;
        this.minutelySummary = minutelySummary;
        this.minutelyIcon = minutelyIcon;
        this.minutelyData = minutelyData;
        this.hourlySummary = hourlySummary;
        this.hourlyIcon = hourlyIcon;
        this.hourlyData = hourlyData;
        this.dailySummary = dailySummary;
        this.dailyIcon = dailyIcon;
        this.dailyData = dailyData;
        this.alerts = alerts;
        this.isDarkskyUnavailable = isDarkskyUnavailable;
        this.unavailableInfo = unavailableInfo;
        this.sources = sources;
        this.units = units;
    }


    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getTimezone() {
        return timezone;
    }

    public ForecastDataPointCurrently getCurrentlyData() {
        return currentlyData;
    }

    public String getMinutelySummary() {
        return minutelySummary;
    }

    public String getMinutelyIcon() {
        return minutelyIcon;
    }

    public List<ForecastDataPointMinutely> getMinutelyData() {
        return minutelyData;
    }

    public String getHourlySummary() {
        return hourlySummary;
    }

    public String getHourlyIcon() {
        return hourlyIcon;
    }

    public List<ForecastDataPointHourly> getHourlyData() {
        return hourlyData;
    }

    public String getDailySummary() {
        return dailySummary;
    }

    public String getDailyIcon() {
        return dailyIcon;
    }

    public List<ForecastDataPointDaily> getDailyData() {
        return dailyData;
    }

    public List<ForecastDataAlert> getAlerts() {
        return alerts;
    }

    public boolean isIsDarkskyUnavailable() {
        return isDarkskyUnavailable;
    }

    public String getUnavailableInfo() {
        return unavailableInfo;
    }

    public List<String> getSources() {
        return sources;
    }

    public String getUnits() {
        return units;
    }
    
    
    
}
