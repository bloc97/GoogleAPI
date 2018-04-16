/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bloc97.api.google;

import org.bloc97.api.UrlBuilder;
import org.bloc97.api.HttpUtils;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author bowen
 */
public class GoogleAPI {
    
    public final static String DIRECTIONS_API_URL = "https://maps.googleapis.com/maps/api/directions/json?";
    public final static String GEOCODING_API_URL = "https://maps.googleapis.com/maps/api/geocode/json?";
    
    /**
     * Requests directions data from Google.
     * @param request
     * @return DirectionsData
     * @throws IOException
     */
    public DirectionsData requestDirections(DirectionsRequest request) throws IOException {
        
        //Create the URL
        UrlBuilder builder = new UrlBuilder(DIRECTIONS_API_URL);
        
        builder.append("origin", request.getOrigin());
        builder.append("destination", request.getDestination());
        
        if (request.getMode() != null && request.getMode() != DirectionsRequest.Mode.DEFAULT) {
            builder.append("mode", request.getMode().toString());
        }
        
        //Handle cases of transit mode passed to the builder
        if (request.getMode() == DirectionsRequest.Mode.DRIVING || request.getMode() == DirectionsRequest.Mode.DEFAULT || request.getMode() == null) {
            if (request.getTrafficModel() != null && request.getTrafficModel() != DirectionsRequest.TrafficModel.DEFAULT) {
                builder.append("traffic_model", request.getTrafficModel().toString());
                if (request.getTime() == null) {
                    builder.append("departure_time", "now");
                }
            }
        }
        
        //If transit mode is not "TRANSIT", use newton's method to find arrival time as the Google API only allows "TRANSIT" to check arrival times
        if (request.getMode() != DirectionsRequest.Mode.TRANSIT) {
            if (request.getTime() != null) {
                if (request.isDepartureTime()) {
                    builder.append("departure_time", Integer.toString((int)(request.getTime().getTime() / 1000)));
                } else {
                    //MULTIPLE API CALLS USING NEWTON'S METHOD, SINCE ONLY TRANSIT CAN USE ARRIVAL TIME
                    int maxIterations = 4; //4 iterations is more than enough for precision, 2-3 is faster

                    Date tempDate = request.getTime(); //Arrival time
                    DirectionsData tempData = null;
                    DirectionsRequestBuilder tempRequest = new DirectionsRequestBuilder().set(request);

                    for (int i=0; i<maxIterations; i++) {
                        tempRequest.setDepartureTime(tempDate);
                        tempData = requestDirections(tempRequest.build());
                        int timeDiff = (int)((tempData.getArrivalTime().getTime() - request.getTime().getTime()) / 1000); //Gradient descent
                        if (timeDiff < 60) { //If difference in time is smaller than 1 min, stop iterating
                            break;
                        }
                        tempDate = new Date(((tempData.getDepartureTime().getTime() / 1000) - timeDiff) * 1000);

                        if (tempDate.getTime() < new Date().getTime()) { //If date is before now, stop gradient descent and depart right now
                            tempRequest.setDepartureTime(null);
                            return requestDirections(tempRequest.build());
                        }
                    }

                    return tempData;
                }
            }
        } else { //If is "TRANSIT", can directly request arrival time
            if (request.getTime() != null) {
                if (request.isDepartureTime()) {
                    builder.append("departure_time", Integer.toString((int)(request.getTime().getTime() / 1000)));
                } else {
                    builder.append("arrival_time", Integer.toString((int)(request.getTime().getTime() / 1000)));
                }
            }
        }
        
        builder.append("key", request.getApiKey());
        
        String response = HttpUtils.httpRequest(builder.getUrl());
        //Parse the JSON data
        JsonObject object = HttpUtils.parseJson(response);
        
        
        //Handle the cases where user requested "TRANSIT" or other modes
        try {
            if (request.getMode() == DirectionsRequest.Mode.TRANSIT) {
                JsonObject legs = object.getAsJsonArray("routes").get(0).getAsJsonObject().getAsJsonArray("legs").get(0).getAsJsonObject();
                String startAddress = legs.get("start_address").getAsString();
                String endAddress = legs.get("end_address").getAsString();
                int distance = legs.getAsJsonObject("distance").get("value").getAsInt();
                int duration = legs.getAsJsonObject("duration").get("value").getAsInt();
                
                //Since mode is "TRANSIT", we can directly obtain the departure/arrival times
                int departure = legs.getAsJsonObject("departure_time").get("value").getAsInt();
                int arrival = legs.getAsJsonObject("arrival_time").get("value").getAsInt();

                return new DirectionsData(startAddress, endAddress, distance, duration, departure, arrival);
            } else {
                JsonObject legs = object.getAsJsonArray("routes").get(0).getAsJsonObject().getAsJsonArray("legs").get(0).getAsJsonObject();
                String startAddress = legs.get("start_address").getAsString();
                String endAddress = legs.get("end_address").getAsString();
                int distance = legs.getAsJsonObject("distance").get("value").getAsInt();
                int duration = legs.getAsJsonObject("duration").get("value").getAsInt();
                
                //If mode is not "TRANSIT", we have to compute the departure/arrival times ourselves
                int departure = request.isDepartureTime() ? (int)(request.getTime().getTime() / 1000) : (int)(new Date().getTime() / 1000);
                int arrival = departure + duration;

                return new DirectionsData(startAddress, endAddress, distance, duration, departure, arrival);
            }
        } catch (Exception e) {
            //Something happened!, return an empty data object
            return new DirectionsData(request.getOrigin(), request.getDestination(), 0, 0, 0, 0);
        }
        
    }
    
    /**
     * Requests geocoding data from Google.
     * @param request
     * @return List of GeocodingData
     * @throws IOException
     */
    public List<GeocodingData> requestGeocoding(GeocodingRequest request) throws IOException {
        
        //Create the URL
        UrlBuilder builder = new UrlBuilder(GEOCODING_API_URL);
        
        builder.append("address", request.getLocation());
        builder.append("key", request.getKey());
        
        
        String response = HttpUtils.httpRequest(builder.getUrl());
        
        //Parse the requested JSON
        JsonObject object = HttpUtils.parseJson(response);
        
        LinkedList<GeocodingData> list = new LinkedList<>();
        
        //Add the information to the list
        try {
            JsonArray results = object.getAsJsonArray("results");
            
            for (JsonElement element : results) {
                String address = element.getAsJsonObject().get("formatted_address").getAsString();
                JsonObject location = element.getAsJsonObject().getAsJsonObject("geometry").getAsJsonObject("location");
                double latitude = location.get("lat").getAsDouble();
                double longitude = location.get("lng").getAsDouble();
                list.add(new GeocodingData(address, latitude, longitude));
            }
        } catch (Exception e) {
        }
        
        return list;
    }
    
}
