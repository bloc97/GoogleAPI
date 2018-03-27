/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bloc97.googleapi;

import com.google.gson.JsonObject;
import java.io.IOException;
import java.util.Date;

/**
 *
 * @author bowen
 */
public class Test {
    
    
    public static DirectionsData getDirections(String origin, String dest, String apikey) throws IOException {
        return getDirections(origin, dest, Main.Mode.DEFAULT, apikey);
    }
    
    public static DirectionsData getDirections(String origin, String dest, Main.Mode mode, String apikey) throws IOException {
        return getDirections(origin, dest, mode, Main.TimeMode.NONE, null, apikey);
    }
    
    public static DirectionsData getDirections(String origin, String dest, Main.Mode mode, Main.TimeMode timeMode, Date date, String apikey) throws IOException {
        return getDirectionsMethod(origin, dest, mode, timeMode, date, Main.TrafficModel.DEFAULT, apikey);
    }
    
    public static DirectionsData getDirections(String origin, String dest, Main.Mode mode, Main.TrafficModel trafficModel, String apikey) throws IOException {
        return getDirections(origin, dest, mode, new Date(), trafficModel, apikey);
    }
    
    public static DirectionsData getDirections(String origin, String dest, Main.Mode mode, Date departureTime, Main.TrafficModel trafficModel, String apikey) throws IOException {
        return getDirectionsMethod(origin, dest, mode, Main.TimeMode.DEPARTURE_TIME, departureTime, trafficModel, apikey);
    }
    private static DirectionsData getDirectionsMethod(String origin, String dest, Main.Mode mode, Main.TimeMode timeMode, Date date, Main.TrafficModel trafficModel, String apikey) throws IOException {
        
        UrlBuilder builder = new UrlBuilder("https://maps.googleapis.com/maps/api/directions/json?");
        
        builder.append("origin", origin);
        builder.append("destination", dest);
        if (mode != Main.Mode.DEFAULT) {
            builder.append("mode", mode.toString());
        }
        if (timeMode != Main.TimeMode.NONE) {
            builder.append(timeMode.toString(), Integer.toString((int)(date.getTime() / 1000)));
        }
        if (trafficModel != Main.TrafficModel.DEFAULT) {
            builder.append("traffic_model", trafficModel.toString());
        }
        builder.append("key", apikey);
        
        
        String response = HttpUtils.httpRequest(builder.getUrl());
        
        JsonObject object = HttpUtils.parseJson(response);
        
        System.out.println(builder.getUrl());
        
        
        try {
            JsonObject legs = object.getAsJsonArray("routes").get(0).getAsJsonObject().getAsJsonArray("legs").get(0).getAsJsonObject();
            String startAddress = legs.get("start_address").getAsString();
            String endAddress = legs.get("end_address").getAsString();
            int distance = legs.getAsJsonObject("distance").get("value").getAsInt();
            int duration = legs.getAsJsonObject("duration").get("value").getAsInt();
            
            int departure = 0;
            int arrival = 0;
            
            if (mode == Main.Mode.TRANSIT) {
                departure = legs.getAsJsonObject("departure_time").get("value").getAsInt();
                arrival = legs.getAsJsonObject("arrival_time").get("value").getAsInt();
            } else {
                switch (timeMode) {
                    case NONE:
                        departure = (int)(new Date().getTime() / 1000);
                        arrival = departure + duration;
                        break;
                    case DEPARTURE_TIME:
                        departure = (int)(date.getTime() / 1000);
                        arrival = departure + duration;
                        break;
                    case ARRIVAL_TIME:
                        arrival = (int)(date.getTime() / 1000);
                        departure = arrival - duration;
                        break;
                    default:
                        break;
                }
            }
            
            //System.out.println(response);
            return new DirectionsData(startAddress, endAddress, distance, duration, departure, arrival);
        } catch (Exception e) {
            return new DirectionsData(origin, dest, 0, 0, 0, 0);
        }
        
    }
    
    
}
