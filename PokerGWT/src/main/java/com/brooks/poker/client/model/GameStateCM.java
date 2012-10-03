package com.brooks.poker.client.model;

import java.io.Serializable;
import java.util.List;

/**
 * @author Trevor
 * 
 */
public class GameStateCM implements Serializable{
    private static final long serialVersionUID = 1L;

    private List<User> allUsers;
    private PotState potState;
    private List<CardCM> communityCards;

    private String channelKey;
    private String actionOnUserName;
    private int minRaiseAmount;
    private boolean started;

    public List<User> getAllUsers(){
        return allUsers;
    }

    public void setAllUsers(List<User> allUsers){
        this.allUsers = allUsers;
    }

    public PotState getPotState(){
        return potState;
    }

    public void setPotState(PotState potState){
        this.potState = potState;
    }

    public List<CardCM> getCommunityCards(){
        return communityCards;
    }

    public void setCommunityCards(List<CardCM> communityCards){
        this.communityCards = communityCards;
    }
    
    public String getActionOnUserName(){
        return actionOnUserName;
    }

    public void setActionOnUserName(String actionOnUserName){
        this.actionOnUserName = actionOnUserName;
    }

    public int getMinRaiseAmount(){
        return minRaiseAmount;
    }

    public void setMinRaiseAmount(int minRaiseAmount){
        this.minRaiseAmount = minRaiseAmount;
    }

    public boolean isStarted(){
        return started;
    }

    public void setStarted(boolean started){
        this.started = started;
    }

    public String getChannelKey(){
        return channelKey;
    }

    public void setChannelKey(String channelKey){
        this.channelKey = channelKey;
    }

}
