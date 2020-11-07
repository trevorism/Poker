package com.brooks.poker.cards;

import java.util.Collections;
import java.util.List;

import com.brooks.poker.cards.Card.Value;
import com.brooks.poker.util.EnumPrinter;

/**
 * @author Trevor
 * 
 */
public final class HandValue implements Comparable<HandValue>{

    public static HandValue NOT_VALID_HAND = new HandValue(HandValueType.NULL_VALUE, Collections.emptyList());

    public enum HandValueType{
        NULL_VALUE, HIGH_CARD, PAIR, TWO_PAIR, THREE_OF_A_KIND, STRAIGHT, FLUSH, FULL_HOUSE, FOUR_OF_A_KIND, STRAIGHT_FLUSH
    }

    private final HandValueType type;
    private final List<Value> tieBreaker;

    public HandValue(HandValueType type, List<Value> tieBreaker){
        this.type = type;
        this.tieBreaker = tieBreaker;
    }

    public boolean isValidHandValue(){
        return type != HandValueType.NULL_VALUE;
    }

    public Value getTieBreakerAt(int index){
        return tieBreaker.get(index);
    }

    public HandValueType getType(){
        return type;
    }

    @Override
    public int compareTo(HandValue handValue){
        int value = CardUtils.compareInts(this.type.ordinal(), handValue.type.ordinal());
        if (value != 0)
            return value;

        return compareTieBreakers(handValue);
    }

    @Override
    public int hashCode(){
        final int prime = 31;
        int result = 1;
        result = prime * result + tieBreaker.hashCode();
        result = prime * result + type.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj)
            return true;
        if (getClass() != obj.getClass())
            return false;
        HandValue other = (HandValue) obj;
        if (!tieBreaker.equals(other.tieBreaker))
            return false;
        if (type != other.type)
            return false;
        return true;
    }

    public String toString(){
    	return EnumPrinter.convertCase(type) + " " + tieBreaker.toString();
    }
    
    private int compareTieBreakers(HandValue handValue){
        int value = 0;
        for (int i = 0; i < this.tieBreaker.size(); i++){
            Value thisValue = this.tieBreaker.get(i);
            Value otherValue = handValue.tieBreaker.get(i);

            value = CardUtils.compareInts(thisValue.ordinal(), otherValue.ordinal());
            if (value != 0)
                return value;
        }
        return value;
    }

}
