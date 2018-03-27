/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bloc97.googleapi;

/**
 *
 * @author bowen
 */
public class UrlBuilder {
    
    private String url;
    private int entries = 0;
    
    public UrlBuilder(String url) {
        this.url = url;
    }
    
    public final void append(String key, String value) {
        if (entries > 0) {
            url += "&";
        }
        url += key + "=" + value;
        entries++;
    }
    
    public final String getUrl() {
        return url.replace(' ', '+');
    }

    public final int getEntriesNum() {
        return entries;
    }
    
    
}
