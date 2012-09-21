package com.brooks.poker.client.model;

/**
 * @author Trevor
 * 
 */
public class Card{

    public static final Card NULL_CARD = new Card("", "");
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

    public boolean isNull(){
        if (value == null || suit == null)
            return true;
        if (value == "" || suit == "")
            return true;
        return false;
    }

    @Override
    public String toString(){
        return value + "_" + suit;
    }
}
