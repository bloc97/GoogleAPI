/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bloc97.api;

/**
 *
 * @author bowen
 */
public class UrlBuilder {
    
    private String url;
    private int entries = 0;
    
    /**
     * Creates an UrlBuilder used for sending HTTP Requests
     * @param url target base URL
     */
    public UrlBuilder(String url) {
        this.url = url;
    }
    
    /**
     * Appends an query to the end of the URL, automatically adding necessary delimiters.
     * @param key
     * @param value
     */
    public final void append(String key, String value) {
        if (entries == 0) {
            url += "?";
        } else {
            url += "&";
        }
        url += key + "=" + value;
        entries++;
    }
    
    /**
     * @return the full formatted URL
     */
    public final String getUrl() {
        return url.replace(' ', '+');
    }

    /**
     * @return numbers of entries in the query
     */
    public final int getEntriesNum() {
        return entries;
    }
    
    
}
