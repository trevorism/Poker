package com.brooks.poker.client;

import com.brooks.poker.client.display.ViewFactory;
import com.google.gwt.core.client.GWT;

/**
 * @author Trevor
 *
 */
public class PokerApplication{
    
    private static PokerServiceAsync service;
    private static ViewFactory viewFactory;
    
    public static PokerServiceAsync getService(){
        if (service == null)
            service = GWT.create(PokerService.class);
        return service;
    }
    
    public static ViewFactory getViewFactory(){
        if (viewFactory == null)
            viewFactory = new ViewFactory();
        return viewFactory;
    }
}
