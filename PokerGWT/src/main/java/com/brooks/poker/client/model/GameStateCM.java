package com.brooks.poker.client.model;

import java.io.Serializable;

/**
 * @author Trevor
 * 
 */
public class GameStateCM implements Serializable{
    private PotState potState;
    private Card[] communityCards;
    private User userToBet;

    public PotState getPotState(){
        return potState;
    }

    public void setPotState(PotState potState){
        this.potState = potState;
    }

    public Card[] getCommunityCards(){
        return communityCards;
    }

    public void setCommunityCards(Card[] communityCards){
        this.communityCards = communityCards;
    }

    public User getUserToBet(){
        return userToBet;
    }

    public void setUserToBet(User userToBet){
        this.userToBet = userToBet;
    }

}
