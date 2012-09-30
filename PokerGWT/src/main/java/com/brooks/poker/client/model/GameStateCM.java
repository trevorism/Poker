package com.brooks.poker.client.model;

import java.io.Serializable;
import java.util.List;

/**
 * @author Trevor
 * 
 */
public class GameStateCM implements Serializable{
    private static final long serialVersionUID = 1L;

    private String serverPushChannel;
    private List<User> allUsers;
    private PotState potState;
    private List<CardCM> communityCards;

    private int usersTurnIndex = -1;
    private int minRaiseAmount;
    
    public String getServerPushChannel(){
        return serverPushChannel;
    }

    public void setServerPushChannel(String serverPushChannel){
        this.serverPushChannel = serverPushChannel;
    }

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

    public int getUsersTurnIndex(){
        return usersTurnIndex;
    }

    public void setUsersTurnIndex(int usersTurnIndex){
        this.usersTurnIndex = usersTurnIndex;
    }

    public int getMinRaiseAmount(){
        return minRaiseAmount;
    }

    public void setMinRaiseAmount(int minRaiseAmount){
        this.minRaiseAmount = minRaiseAmount;
    }

}
