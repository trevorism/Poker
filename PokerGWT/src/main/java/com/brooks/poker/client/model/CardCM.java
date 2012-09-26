package com.brooks.poker.client.model;

import java.io.Serializable;

/**
 * @author Trevor
 * 
 */
public class CardCM implements Serializable{
    private static final long serialVersionUID = 1L;
    
    public static final CardCM NULL_CARD = new CardCM();
    private String value;
    private String suit;

    public CardCM(){
        this("", "");
    }
    
    public CardCM(String value, String suit){
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
