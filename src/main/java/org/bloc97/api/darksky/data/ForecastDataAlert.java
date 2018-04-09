/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bloc97.api.darksky.data;

import java.util.Date;
import java.util.List;

/**
 *
 * @author bowen
 */
public class ForecastDataAlert {
    
    private final String description;
    private final Date expires;
    private final List<String> regions;
    private final String severity; //advisory, watch, warning
    private final Date time;
    private final String title;
    private final String uri;

    public ForecastDataAlert(String description, Date expires, List<String> regions, String severity, Date time, String title, String uri) {
        this.description = description;
        this.expires = expires;
        this.regions = regions;
        this.severity = severity;
        this.time = time;
        this.title = title;
        this.uri = uri;
    }

    public String getDescription() {
        return description;
    }

    public Date getExpires() {
        return expires;
    }

    public List<String> getRegions() {
        return regions;
    }

    public String getSeverity() {
        return severity;
    }

    public Date getTime() {
        return time;
    }

    public String getTitle() {
        return title;
    }

    public String getUri() {
        return uri;
    }
    
    
    
}
