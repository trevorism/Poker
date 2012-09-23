package com.brooks.poker.client.event;

import com.brooks.common.client.event.Event;
import com.brooks.poker.client.model.GameStateCM;
import com.brooks.poker.client.model.User;

/**
 * @author Trevor
 *
 */
public class FoldEvent implements Event{

    private User foldingUser;
    private GameStateCM gameState;
    
    public FoldEvent(User user, GameStateCM result){
        this.foldingUser = user;
        this.gameState = result;
    }

    public User getFoldingUser(){
        return foldingUser;
    }

    public GameStateCM getGameState(){
        return gameState;
    }

}
