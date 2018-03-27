/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bloc97.googleapi;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;

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
