package com.brooks.poker.client.model;

import java.io.Serializable;

/**
 * @author Trevor
 * 
 */
public class Card implements Serializable{
    private static final long serialVersionUID = 1L;
    
    public static final Card NULL_CARD = new Card();
    private String value;
    private String suit;

    public Card(){
        this("", "");
    }
    
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
