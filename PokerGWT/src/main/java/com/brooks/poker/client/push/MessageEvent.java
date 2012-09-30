package com.brooks.poker.client.push;

import no.eirikb.gwtchannelapi.client.Message;

/**
 * @author Trevor
 *
 */
public class MessageEvent implements Message{

    private static final long serialVersionUID = 1L;
    
    private String message;

    public MessageEvent() {
    }

    public MessageEvent(String message) {
        this.message = message;
    }

    public String toString() {
        return message;
    }

}
