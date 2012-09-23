package com.brooks.poker.client.event;

import com.brooks.common.client.event.Event;
import com.brooks.poker.client.model.GameStateCM;
import com.brooks.poker.client.model.User;

/**
 * @author Trevor
 *
 */
public class CallEvent implements Event{

    private User callingUser;
    private GameStateCM gameState;
    
    public CallEvent(User user, GameStateCM result){
        this.callingUser = user;
        this.gameState = result;
    }

    public User getFoldingUser(){
        return callingUser;
    }

    public GameStateCM getGameState(){
        return gameState;
    }
}
