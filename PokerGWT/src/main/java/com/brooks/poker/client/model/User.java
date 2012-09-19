package com.brooks.poker.client.model;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Trevor
 * 
 */
public class User{
    private String name;
    private int chips;
    private List<Card> cards;
    private int currentBet;
    private boolean sitting;
    private boolean inHand;

    public User(){
        cards = new LinkedList<>();
    }

    public void addCard(Card card){
        cards.add(card);
    }

    public void clearCards(){
        cards.clear();
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getChips(){
        return chips;
    }

    public void setChips(int chips){
        this.chips = chips;
    }

    public int getCurrentBet(){
        return currentBet;
    }

    public void setCurrentBet(int currentBet){
        this.currentBet = currentBet;
    }

    public boolean isSitting(){
        return sitting;
    }

    public void setSitting(boolean sitting){
        this.sitting = sitting;
    }

    public boolean isInHand(){
        return inHand;
    }

    public void setInHand(boolean inHand){
        this.inHand = inHand;
    }

}
