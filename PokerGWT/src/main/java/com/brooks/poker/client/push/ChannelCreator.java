package com.brooks.poker.client.push;

import no.eirikb.gwtchannelapi.client.Channel;
import no.eirikb.gwtchannelapi.client.ChannelListener;
import no.eirikb.gwtchannelapi.client.Message;

/**
 * @author Trevor
 *
 */
public class ChannelCreator{

    private boolean initialized = false;
    
    public void setChannelToken(String token){
        if(initialized)
            return;
        
        Channel channel = new Channel(token);
        channel.addChannelListener(new ChannelListener(){
            
            @Override
            public void onReceive(Message message){
           
            }
        });
        initialized = true;
    }
}
