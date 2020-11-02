package com.brooks.poker.cards;

import com.brooks.poker.util.EnumPrinter;

/**
 * @author Trevor
 * 
 */
public final class Card implements Comparable<Card>{

    public static final Card NULL_CARD = new Card(Suit.CLUBS, Value.NULL);

    public enum Suit{
        CLUBS, DIAMONDS, HEARTS, SPADES
    }

    public enum Value{
        NULL, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE
    }

    private final Suit suit;
    private final Value value;

    public Card(Suit suit, Value value){
        this.suit = suit;
        this.value = value;
    }

    public Suit getSuit(){
        return suit;
    }

    public Value getValue(){
        return value;
    }

    public boolean isNullCard(){
        return value == Value.NULL;
    }

    public int compareTo(Card o){
        return CardUtils.compareInts(this.value.ordinal(), o.value.ordinal());
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(getValue().toString());
        sb.append(" of ");
        sb.append(getSuit().toString());
        return EnumPrinter.convertCase(sb.toString());
    }

    @Override
    public int hashCode(){
        final int prime = 31;
        int result = 1;
        result = prime * result + suit.hashCode();
        result = prime * result + value.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj)
            return true;
        if (getClass() != obj.getClass())
            return false;
        final Card other = (Card) obj;

        if (!suit.equals(other.suit))
            return false;
        if (!value.equals(other.value))
            return false;
        return true;
    }

}
