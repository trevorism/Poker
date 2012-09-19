package com.brooks.poker.client.model;


/**
 * @author Trevor
 *
 */
public class Card{
    
    private final String value;
    private final String suit;

    public Card(String value, String suit){
        super();
        this.value = value.toUpperCase();
        this.suit = suit.toUpperCase();
    }

    public String getValue(){
        return value;
    }

    public String getSuit(){
        return suit;
    }

    
    @Override
    public String toString(){        
        return value + "_" + suit;
    }
}
