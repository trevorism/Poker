package com.brooks.poker.client.push;

import no.eirikb.gwtchannelapi.client.Message;

import com.brooks.common.client.event.Event;
import com.brooks.poker.client.model.User;

/**
 * @author Trevor
 * 
 */
public class UserMessage implements Message, Event{

    private static final long serialVersionUID = 1L;

    private User user;
    private int index;
    
    public UserMessage(){
    }

    public UserMessage(User user, int index){
        this.user = user;
        this.index = index;
    }

    public User getUser(){
        return user;
    }

    public int getIndex(){
        return index;
    }  

}
