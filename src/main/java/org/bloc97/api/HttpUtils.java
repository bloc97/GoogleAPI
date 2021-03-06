/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bloc97.api;

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
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author bowen
 */
public interface HttpUtils {
    
    /**
     * Parses a JSON string into a JsonObject
     * @param jsonString an json-formatted string
     * @return a JsonObject representing the json string
     */
    public static JsonObject parseJson(String jsonString) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonObject object = new JsonParser().parse(jsonString).getAsJsonObject();
        
        return object;
    }
    
    /**
     * Sends an HTTP Request
     * @param urlString url target of request
     * @return result of the request
     * @throws MalformedURLException
     * @throws IOException
     */
    public static String httpRequest(String urlString) throws MalformedURLException, IOException {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
        
        String s;
        String output = null;
        
        while ((s = reader.readLine()) != null) { //Read until string is empty
            if (output == null) {
                output = s;
            } else {
                output += "\n" + s; //Newline
            }
        }
        reader.close();
        connection.disconnect();
        return output;
    }
}
