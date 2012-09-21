package com.brooks.poker.client.model;


/**
 * @author Trevor
 * 
 */
public class User{
    private String name;
    private int chips;
    private Card card1;
    private Card card2;
    private int currentBet;
    private boolean sitting;
    private boolean inHand;

    public User(){
        clearCards();
    }

    public void clearCards(){
        card1 = Card.NULL_CARD;
        card2 = Card.NULL_CARD;
    }    
    
    public Card getCard1(){
        return card1;
    }

    public void setCard1(Card card1){
        this.card1 = card1;
    }

    public Card getCard2(){
        return card2;
    }

    public void setCard2(Card card2){
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
