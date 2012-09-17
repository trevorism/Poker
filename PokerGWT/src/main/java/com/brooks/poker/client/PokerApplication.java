package com.brooks.poker.client;

import com.google.gwt.core.client.GWT;

/**
 * @author Trevor
 *
 */
public class PokerApplication{
    
    private static PokerServiceAsync service;
    
    public static PokerServiceAsync getService(){
        if (service == null)
            service = GWT.create(PokerService.class);
        return service;
    }
}
