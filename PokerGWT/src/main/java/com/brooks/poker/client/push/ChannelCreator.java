package com.brooks.poker.client.push;

import com.brooks.common.client.callback.Callback;
import com.brooks.common.client.event.EventBus;
import com.brooks.poker.client.PokerApplication;
import com.google.gwt.user.client.Timer;

/**
 * @author Trevor
 * 
 */
public class ChannelCreator{

    public void connect(){
        PokerApplication.getService().connectToChannel(new Callback<String>(){
            @Override
            public void onSuccess(String result){
                schedulePoller(result);
            }
        });
    }

    private void schedulePoller(final String token){
        Timer timer = new Timer(){
            @Override
            public void run(){
                PokerApplication.getService().receiveServerPush(token, new Callback<PushEvent>(){
                    @Override
                    public void onSuccess(PushEvent message){
                        if(message == null)
                            return;
                        if(message instanceof GameStateMessage)
                            EventBus.getInstance().fireEvent((GameStateMessage) message);
                    }
                });
            }
        };
        timer.scheduleRepeating(5000);
    }
}
