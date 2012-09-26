package com.brooks.poker.client.model;

import java.io.Serializable;

/**
 * @author Trevor
 * 
 */
public class Action implements Serializable{

    private static final long serialVersionUID = 1L;

    public enum UserAction{
        FOLD, CALL, RAISE
    }

    private long gameId;
    private UserAction action;
    private int betAmount;

    public long getGameId(){
        return gameId;
    }

    public void setGameId(long gameId){
        this.gameId = gameId;
    }

    public UserAction getAction(){
        return action;
    }

    public void setAction(UserAction action){
        this.action = action;
    }

    public int getBetAmount(){
        return betAmount;
    }

    public void setBetAmount(int betAmount){
        this.betAmount = betAmount;
    }

}
