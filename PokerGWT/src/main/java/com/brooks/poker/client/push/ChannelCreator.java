package com.brooks.poker.client.push;

import no.eirikb.gwtchannelapi.client.Channel;
import no.eirikb.gwtchannelapi.client.ChannelListener;
import no.eirikb.gwtchannelapi.client.Message;

import com.brooks.common.client.callback.Callback;
import com.brooks.common.client.event.EventBus;
import com.brooks.poker.client.PokerApplication;

/**
 * @author Trevor
 * 
 */
public class ChannelCreator{

    public ChannelCreator(){
        PokerApplication.getService().connectToChannel(new Callback<String>(){
            @Override
            public void onSuccess(String result){
                setChannelToken(result);
            }
        });
    }

    public void setChannelToken(String token){
        Channel channel = new Channel(token);
        channel.join();
        channel.addChannelListener(new ChannelListener(){

            @Override
            public void onReceive(Message message){
                if(message instanceof UserMessage){
                    EventBus.getInstance().fireEvent((UserMessage) message);
                }
            }
        });
    }
}
