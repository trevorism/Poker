package com.brooks.poker.client.model;

import java.io.Serializable;


/**
 * @author Trevor
 * 
 */
public class User implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private String name;
    private int chips;
    private CardCM card1;
    private CardCM card2;
    private int pendingBet;
    private boolean sitting;
    private boolean inHand;

    public User(){
        clearCards();
    }

    public void clearCards(){
        card1 = CardCM.NULL_CARD;
        card2 = CardCM.NULL_CARD;
    }    
    
    public CardCM getCard1(){
        return card1;
    }

    public void setCard1(CardCM card1){
        this.card1 = card1;
    }

    public CardCM getCard2(){
        return card2;
    }

    public void setCard2(CardCM card2){
        this.card2 = card2;
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

    public int getPendingBet(){
        return pendingBet;
    }

    public void setPendingBet(int currentBet){
        this.pendingBet = currentBet;
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
