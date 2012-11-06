package com.brooks.poker.client.push;

import com.brooks.common.client.event.Event;
import com.brooks.poker.client.model.User;

/**
 * @author Trevor
 * 
 */
public class UpdateActionsEvent implements Event{

    private final User user;
    private final int minBet;
    private final boolean gameStarted;

    public UpdateActionsEvent(User user, int minBet, boolean gameStarted){
        this.user = user;
        this.minBet = minBet;
        this.gameStarted = gameStarted;
    }

    public User getUser(){
        return user;
    }

    public int getMinBet(){
        return minBet;
    }

    public boolean isGameStarted(){
        return gameStarted;
    }

}
