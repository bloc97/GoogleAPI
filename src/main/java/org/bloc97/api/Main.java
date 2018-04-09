/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bloc97.api;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import org.bloc97.api.darksky.DarkSkyAPI;
import org.bloc97.api.darksky.ForecastData;
import org.bloc97.api.darksky.ForecastDataPointMinutely;
import org.bloc97.api.darksky.ForecastRequest;
import org.bloc97.api.darksky.ForecastRequestBuilder;
import org.bloc97.api.google.DirectionsData;
import org.bloc97.api.google.DirectionsRequest;
import org.bloc97.api.google.GeocodingRequest;
import org.bloc97.api.google.GeocodingRequestBuilder;
import org.bloc97.api.google.GoogleAPI;

/**
 *
 * @author bowen
 */
public class Main {
    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        
        String darkskyapi = new String(Files.readAllBytes(Paths.get("dapi.key")), StandardCharsets.UTF_8);
        
        DarkSkyAPI dapi = new DarkSkyAPI();
        
        ForecastRequestBuilder b = new ForecastRequestBuilder();
        b.setKey(darkskyapi);
        b.setLatitude(45);
        b.setLongitude(-73);
        b.setUnits(ForecastRequest.Units.CA);
        ForecastData d = dapi.requestForecast(b.build());
        System.out.println(d.getUnits());
        System.out.println(d.getCurrentlyData().getApparentTemperature());
        System.out.println(d.getCurrentlyData().getTemperature());
        System.out.println(d.getCurrentlyData().getHumidity());
        System.out.println(d.getCurrentlyData().getPressure());
        System.out.println("UNAVAILABLE: " + d.isIsDarkskyUnavailable() + " INFO: " + d.getUnavailableInfo());
        List<ForecastDataPointMinutely> minutely = d.getMinutelyData();
        
        for (ForecastDataPointMinutely m : minutely) {
            System.out.println(m.getPrecipProbability());
        }
        
        String apikey = new String(Files.readAllBytes(Paths.get("api.key")), StandardCharsets.UTF_8);
        
        GoogleAPI api = new GoogleAPI();
        
        DirectionsRequest request = DirectionsRequest.builder().setOrigin("Universite de Montreal")
                .setDestination("Bibliotheque Brossard")
                .setMode(DirectionsRequest.Mode.TRANSIT)
                .setArrivalTime(new Date(((new Date().getTime()/1000) + 3400)*1000))
                .setTrafficModel(DirectionsRequest.TrafficModel.BEST_GUESS)
                .setApiKey(apikey).build();
        
        DirectionsData data = api.requestDirections(request);
        System.out.println(data);
        
        GeocodingRequestBuilder grequestbuilder = GeocodingRequest.builder().setKey(apikey);
        
        System.out.println(api.requestGeocoding(grequestbuilder.setLocation("pavillon roger-gaudry").build()));
        System.out.println(api.requestGeocoding(grequestbuilder.setLocation("universite de montreal").build()));
        System.out.println(api.requestGeocoding(grequestbuilder.setLocation("45.26 , -73.17").build()));
        
    }
    
}
