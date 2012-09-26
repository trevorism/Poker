package com.brooks.poker.client.model;

import java.io.Serializable;
import java.util.List;

/**
 * @author Trevor
 * 
 */
public class GameStateCM implements Serializable{
    private static final long serialVersionUID = 1L;

    private long id;
    private List<User> allUsers;
    private PotState potState;
    private List<CardCM> communityCards;

    private User userToBet;
    private int minRaiseAmount;

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

    public User getUserToBet(){
        return userToBet;
    }

    public void setUserToBet(User userToBet){
        this.userToBet = userToBet;
    }

    public int getMinRaiseAmount(){
        return minRaiseAmount;
    }

    public void setMinRaiseAmount(int minRaiseAmount){
        this.minRaiseAmount = minRaiseAmount;
    }

    public long getId(){
        return id;
    }

    public void setId(long id){
        this.id = id;
    }

}
