package com.brooks.poker.client.model;

import java.io.Serializable;

/**
 * @author Trevor
 *
 */
public class Action implements Serializable{

    private static final long serialVersionUID = 1L;
    public enum PlayerAction{
        FOLD, CALL, RAISE
    }
    
    private PlayerAction action;
    private int betAmount;
    
    public PlayerAction getAction(){
        return action;
    }
    public void setAction(PlayerAction action){
        this.action = action;
    }
    public int getBetAmount(){
        return betAmount;
    }
    public void setBetAmount(int betAmount){
        this.betAmount = betAmount;
    }
    
    
}
