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
public class Main {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        
        String apikeyDirections = "AIzaSyBQwW6VVSdWzdFWcHw4V_oYej0LXO8Rjxs";
        String apikeyGeolocation = "AIzaSyDpmomvQOtvQ6zivVfsw7GU-SiBY8bAQHo";
        
        GoogleAPI api = new GoogleAPI();
        
        DirectionsRequest request = DirectionsRequest.builder().setOrigin("Universite de Montreal")
                .setDestination("Bibliotheque Brossard")
                .setMode(DirectionsRequest.Mode.TRANSIT)
                .setArrivalTime(new Date(((new Date().getTime()/1000) + 3400)*1000))
                .setTrafficModel(DirectionsRequest.TrafficModel.BEST_GUESS)
                .setApiKey(apikeyDirections).build();
        
        DirectionsData data = api.requestDirections(request);
        System.out.println(data);
        
        GeocodingRequestBuilder grequestbuilder = GeocodingRequest.builder().setKey(apikeyGeolocation);
        
        System.out.println(api.requestGeocoding(grequestbuilder.setLocation("pavillon roger-gaudry").build()));
        System.out.println(api.requestGeocoding(grequestbuilder.setLocation("universite de montreal").build()));
        System.out.println(api.requestGeocoding(grequestbuilder.setLocation("45.26 , -73.17").build()));
        
    }
    
}
