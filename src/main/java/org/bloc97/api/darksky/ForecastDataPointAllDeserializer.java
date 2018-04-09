/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bloc97.api.darksky;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import java.util.Date;

/**
 *
 * @author bowen
 */
public class ForecastDataPointAllDeserializer implements JsonDeserializer<ForecastDataPointAll> {

    protected static double parseDouble(JsonElement s) {
        if (s != null) {
            try {
                return s.getAsDouble();
            } catch (Exception E) {
            }
        }
        return Double.NaN;
    }
    protected static Date parseDate(JsonElement s) {
        if (s != null) {
            try {
                return new Date(s.getAsInt() * 1000L);
            } catch (Exception E) {
            }
        }
        return null;
    }
    protected static String parseString(JsonElement s) {
        if (s != null) {
            try {
                return s.getAsString();
            } catch (Exception E) {
            }
        }
        return "N/A";
    }
    
    @Override
    public ForecastDataPointAll deserialize(JsonElement je, Type type, JsonDeserializationContext jdc) throws JsonParseException {
        JsonObject o = je.getAsJsonObject();
        return new ForecastDataPointAll(
                parseDouble(o.get("cloudCover")), 
                parseDouble(o.get("dewPoint")), 
                parseDouble(o.get("humidity")), 
                parseString(o.get("icon")), 
                parseDouble(o.get("ozone")), 
                parseDouble(o.get("precipIntensity")), 
                parseDouble(o.get("precipProbability")), 
                parseString(o.get("precipType")), 
                parseDouble(o.get("pressure")), 
                parseString(o.get("summary")), 
                parseDate(o.get("time")), 
                parseDouble(o.get("uvIndex")), 
                parseDouble(o.get("visibility")), 
                parseDouble(o.get("windBearing")), 
                parseDouble(o.get("windGust")),
                parseDouble(o.get("windSpeed")),
                parseDouble(o.get("nearestStormBearing")),
                parseDouble(o.get("nearestStormDistance")),
                parseDouble(o.get("apparentTemperature")),
                parseDouble(o.get("temperature")),
                parseDouble(o.get("precipAccumulation")), 
                parseDouble(o.get("apparentTemperatureHigh")), 
                parseDouble(o.get("apparentTemperatureLow")), 
                parseDate(o.get("apparentTemperatureHighTime")), 
                parseDate(o.get("apparentTemperatureLowTime")), 
                parseDouble(o.get("moonPhase")), 
                parseDouble(o.get("precipIntensityMax")), 
                parseDate(o.get("precipIntensityMaxTime")), 
                parseDouble(o.get("sunriseTime")), 
                parseDouble(o.get("sunsetTime")), 
                parseDouble(o.get("temperatureHigh")), 
                parseDouble(o.get("temperatureLow")), 
                parseDate(o.get("temperatureHighTime")), 
                parseDate(o.get("temperatureLowTime")), 
                parseDate(o.get("uvIndexTime"))
        );
    }
    
}
