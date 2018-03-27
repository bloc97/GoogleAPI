/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bloc97.googleapi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.time.Instant;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import jdk.internal.org.xml.sax.InputSource;
import jdk.nashorn.internal.parser.JSONParser;

/**
 *
 * @author bowen
 */
public class Test2 {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        
        String apikey = "AIzaSyBQwW6VVSdWzdFWcHw4V_oYej0LXO8Rjxs";
        System.out.println(findDirectionsDrivingFromArrivalTime("Universite de Montreal", "Bibliotheque Brossard", new Date(((new Date().getTime()/1000) + 3400)*1000), TrafficModel.BEST_GUESS, apikey, 5));
        
        //Aadd different choices of transport and optimstic/pessimistic check
        
        
    }
    
    public enum Mode {
        WALKING, BICYCLING;
        
        @Override        
        public String toString() {
            return super.toString().toLowerCase();
        }
    }
    
    public enum TimeMode {
        NONE, DEPARTURE_TIME, ARRIVAL_TIME;
        @Override        
        public String toString() {
            return super.toString().toLowerCase();
        }
    }
    
    public enum TrafficModel {
        DEFAULT, BEST_GUESS, PESSIMISTIC, OPTIMISTIC;
        @Override        
        public String toString() {
            return super.toString().toLowerCase();
        }
    }
    
    public static DirectionsData findDirectionsDrivingFromArrivalTime(String origin, String dest, Date arrivalTime, TrafficModel trafficModel, String apiKey, int maxIterations) throws IOException {
        
        Date tempDate = arrivalTime;
        DirectionsData tempData = null;
        
        for (int i=0; i<maxIterations; i++) {
            tempData = getDirectionsDriving(origin, dest, tempDate, trafficModel, apiKey);
            //System.out.println(tempData);
            int timeDiff = (int)((tempData.getArrivalTime().getTime() - arrivalTime.getTime()) / 1000); //Gradient descent
            if (timeDiff < 60) { //If difference in time is smaller than 1 min, stop iterating
                break;
            }
            tempDate = new Date(((tempData.getDepartureTime().getTime() / 1000) - timeDiff) * 1000);
            
            if (tempDate.getTime() < new Date().getTime()) { //If date is before now, stop gradient descent and depart right now
                return getDirectionsDriving(origin, dest, null, trafficModel, apiKey);
            }
        }
        
        return tempData;
    }
    
    public static DirectionsData getDirectionsDriving(String origin, String dest, Date departureTime, TrafficModel trafficModel, String apiKey) throws IOException {
        
        UrlBuilder builder = new UrlBuilder(DIRECTIONS_API_URL);
        
        builder.append("origin", origin);
        builder.append("destination", dest);
        builder.append("mode", "driving");
        if (departureTime != null) {
            builder.append("departure_time", Integer.toString((int)(departureTime.getTime() / 1000)));
        } else if (trafficModel != null && trafficModel != TrafficModel.DEFAULT) {
            builder.append("departure_time", "now");
        }
        if (trafficModel != null && trafficModel != TrafficModel.DEFAULT) {
            builder.append("traffic_model", trafficModel.toString());
        }
        builder.append("key", apiKey);
        
        if (departureTime == null) {
            departureTime = new Date();
        }
        
        String response = HttpUtils.httpRequest(builder.getUrl());
        
        JsonObject object = HttpUtils.parseJson(response);
        
        //System.out.println(builder.getUrl());
        
        
        try {
            JsonObject legs = object.getAsJsonArray("routes").get(0).getAsJsonObject().getAsJsonArray("legs").get(0).getAsJsonObject();
            String startAddress = legs.get("start_address").getAsString();
            String endAddress = legs.get("end_address").getAsString();
            int distance = legs.getAsJsonObject("distance").get("value").getAsInt();
            int duration = legs.getAsJsonObject("duration").get("value").getAsInt();
            
            int departure = (int)(departureTime.getTime() / 1000);
            int arrival = departure + duration;
            
            //System.out.println(response);
            return new DirectionsData(startAddress, endAddress, distance, duration, departure, arrival);
        } catch (Exception e) {
            return new DirectionsData(origin, dest, 0, 0, 0, 0);
        }
    }
    public static DirectionsData getDirectionsTransit(String origin, String dest, TimeMode timeMode, Date date, String apikey) throws IOException {
        
        UrlBuilder builder = new UrlBuilder(DIRECTIONS_API_URL);
        
        builder.append("origin", origin);
        builder.append("destination", dest);
        builder.append("mode", "transit");
        
        if (timeMode != null && timeMode != TimeMode.NONE && date != null) {
            builder.append(timeMode.toString(), Integer.toString((int)(date.getTime() / 1000)));
        }
        builder.append("key", apikey);
        
        
        String response = HttpUtils.httpRequest(builder.getUrl());
        
        JsonObject object = HttpUtils.parseJson(response);
        
        //System.out.println(builder.getUrl());
        
        
        try {
            JsonObject legs = object.getAsJsonArray("routes").get(0).getAsJsonObject().getAsJsonArray("legs").get(0).getAsJsonObject();
            String startAddress = legs.get("start_address").getAsString();
            String endAddress = legs.get("end_address").getAsString();
            int distance = legs.getAsJsonObject("distance").get("value").getAsInt();
            int duration = legs.getAsJsonObject("duration").get("value").getAsInt();
            
            int departure = legs.getAsJsonObject("departure_time").get("value").getAsInt();
            int arrival = legs.getAsJsonObject("arrival_time").get("value").getAsInt();
            
            //System.out.println(response);
            return new DirectionsData(startAddress, endAddress, distance, duration, departure, arrival);
        } catch (Exception e) {
            return new DirectionsData(origin, dest, 0, 0, 0, 0);
        }
        
    }
    
}
