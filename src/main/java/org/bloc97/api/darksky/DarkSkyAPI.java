/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bloc97.api.darksky;

import org.bloc97.api.darksky.data.ForecastDataAlert;
import org.bloc97.api.darksky.data.ForecastDataPointHourly;
import org.bloc97.api.darksky.data.ForecastDataPointAllDeserializer;
import org.bloc97.api.darksky.data.ForecastDataPointCurrently;
import org.bloc97.api.darksky.data.ForecastDataPointDaily;
import org.bloc97.api.darksky.data.ForecastDataPointMinutely;
import org.bloc97.api.darksky.data.ForecastDataPointAll;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import org.bloc97.api.HttpUtils;
import org.bloc97.api.UrlBuilder;

/**
 *
 * @author bowen
 */
public class DarkSkyAPI {
    public final static String FORECAST_API_URL = "https://api.darksky.net/forecast/";
    
    private final static Gson GSON = new GsonBuilder().registerTypeAdapter(ForecastDataPointAll.class, new ForecastDataPointAllDeserializer()).create();

    
    public ForecastData requestForecast(ForecastRequest request) throws IOException {
        
        String fullURL = FORECAST_API_URL + request.getKey() + "/" + 
                 request.getLatitude()  + "," + 
                 request.getLongitude() + (request.getTime() == null ? "?" : "," + request.getTime().getTime()/1000 + "?");
        
        UrlBuilder builder = new UrlBuilder(fullURL);
        if (request.getExcludes() != null && !request.getExcludes().isEmpty()) {
            builder.append("exclude", request.getExcludes().toString().replace("[", "").replace("]", "").replace(" ", ""));
        }
        if (request.isExtendHourly()) {
            builder.append("extend", "hourly");
        }
        if (request.getLanguage() != null && request.getLanguage() != ForecastRequest.Language.DEFAULT) {
            builder.append("lang", request.getLanguage().toString());
        }
        if (request.getUnits()!= null && request.getUnits()!= ForecastRequest.Units.DEFAULT) {
            builder.append("units", request.getUnits().toString());
        }
        
        String response = HttpUtils.httpRequest(builder.getUrl());
        
        JsonObject responseData = HttpUtils.parseJson(response);
        
        List<ForecastDataPointMinutely> minutely = new LinkedList<>();
        String minutelySummary = "N/A";
        String minutelyIcon = "N/A";
        List<ForecastDataPointHourly> hourly = new LinkedList<>();
        String hourlySummary = "N/A";
        String hourlyIcon = "N/A";
        List<ForecastDataPointDaily> daily = new LinkedList<>();
        String dailySummary = "N/A";
        String dailyIcon = "N/A";
        
        JsonObject currentlyData = responseData.getAsJsonObject("currently");
        ForecastDataPointCurrently currently = new ForecastDataPointCurrently(GSON.fromJson(currentlyData, ForecastDataPointAll.class));
        
        try {
            JsonArray minutelyData = responseData.getAsJsonObject("minutely").getAsJsonArray("data");
            for (JsonElement e : minutelyData) {
                minutely.add(new ForecastDataPointMinutely(GSON.fromJson(e, ForecastDataPointAll.class)));
            }
            minutelySummary = responseData.getAsJsonObject("minutely").get("summary").getAsString();
            minutelyIcon = responseData.getAsJsonObject("minutely").get("icon").getAsString();
        } catch (Exception e) {
                
        }
        
        
        try {
            JsonArray hourlyData = responseData.getAsJsonObject("hourly").getAsJsonArray("data");
            for (JsonElement e : hourlyData) {
                hourly.add(new ForecastDataPointHourly(GSON.fromJson(e, ForecastDataPointAll.class)));
            }
            hourlySummary = responseData.getAsJsonObject("hourly").get("summary").getAsString();
            hourlyIcon = responseData.getAsJsonObject("hourly").get("icon").getAsString();
        } catch (Exception e) {
                
        }
        
        try {
            JsonArray dailyData = responseData.getAsJsonObject("daily").getAsJsonArray("data");
            for (JsonElement e : dailyData) {
                daily.add(new ForecastDataPointDaily(GSON.fromJson(e, ForecastDataPointAll.class)));
            }
            dailySummary = responseData.getAsJsonObject("daily").get("summary").getAsString();
            dailyIcon = responseData.getAsJsonObject("daily").get("icon").getAsString();
        } catch (Exception e) {
                
        }
        
        
        List<ForecastDataAlert> alerts = new LinkedList<>();
        
        try {
            JsonArray dailyData = responseData.getAsJsonArray("alerts");
            for (JsonElement e : dailyData) {
                JsonObject o = e.getAsJsonObject();
                
                LinkedList<String> regions = new LinkedList<>();
                
                for (JsonElement se : o.getAsJsonArray("regions")) {
                    regions.add(se.getAsString());
                }
                
                alerts.add(new ForecastDataAlert(
                        o.get("description").getAsString(),
                        ForecastDataPointAllDeserializer.parseDate(o.get("expires")),
                        regions,
                        o.get("severity").getAsString(),
                        ForecastDataPointAllDeserializer.parseDate(o.get("time")),
                        o.get("time").getAsString(),
                        o.get("uri").getAsString()));
            }
            dailySummary = responseData.getAsJsonObject("daily").get("summary").getAsString();
            dailyIcon = responseData.getAsJsonObject("daily").get("icon").getAsString();
        } catch (Exception e) {
                
        }
        
        String units = "N/A";
        String info = "N/A";
        boolean unav = false;
        
        try {
            units = responseData.getAsJsonObject("flags").get("units").getAsString();
        } catch (Exception e) {
                
        }
        try {
            info = responseData.getAsJsonObject("flags").get("darksky-unavailable").getAsString();
            unav = true;
        } catch (Exception e) {
                
        }
        
        ForecastData data = new ForecastData(
                responseData.get("latitude").getAsDouble(),
                responseData.get("longitude").getAsDouble(),
                responseData.get("timezone").getAsString(),
                currently,
                minutelySummary,
                minutelyIcon,
                minutely,
                hourlySummary,
                hourlyIcon,
                hourly, 
                dailySummary,
                dailyIcon,
                daily,
                alerts,
                unav,
                info,
                new LinkedList<String>(),
                units);
        
        return data;
    }
    
}
