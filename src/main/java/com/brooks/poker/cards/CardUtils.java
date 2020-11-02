package com.brooks.poker.cards;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.brooks.poker.cards.Card.Suit;
import com.brooks.poker.cards.Card.Value;

/**
 * @author Trevor
 * 
 */
public class CardUtils{

    public static Value findMaximum(List<Card> cards){
        return findMaximum(cards, Card.Value.ACE.ordinal() + 1);
    }

    public static Value findMaximum(List<Card> cards, int underValueOrdinal){
        Value maxValue = Value.NULL;

        for (Card card : cards){
            if (card.getValue().ordinal() < underValueOrdinal){
                maxValue = maximum(maxValue, card.getValue());
            }
        }
        return maxValue;
    }

    private static Value maximum(Value card1, Value card2){
        if (card1.compareTo(card2) >= 0){
            return card1;
        }
        return card2;
    }

    public static int countValues(List<Card> cards, Value value){
        int count = 0;
        for (Card card : cards){
            if (card.getValue().equals(value)){
                count++;
            }
        }
        return count;
    }

    public static int countSuits(List<Card> cards, Suit suit){
        int count = 0;
        for (Card card : cards){
            if (card.getSuit().equals(suit)){
                count++;
            }
        }
        return count;
    }

    public static Card findCard(List<Card> cards, Card desiredCard){
        for (Card card : cards){
            if (card.equals(desiredCard))
                return card;
        }
        return Card.NULL_CARD;
    }

    public static Card findCard(List<Card> cards, Value value){
        for (Card card : cards){
            if (card.getValue().equals(value))
                return card;
        }
        return Card.NULL_CARD;
    }

    public static Set<Suit> findSuitsForValue(List<Card> cards, Value value){
        Set<Suit> suits = new HashSet<>();
        for (Card card : cards){
            if (card.getValue().equals(value))
                suits.add(card.getSuit());
        }

        return suits;
    }

    public static int compareInts(int val1, int val2){
        return Integer.valueOf(val1).compareTo(val2);
    }

    public static int circularIncrement(int index, int maxValue){
        if (index + 1 >= maxValue)
            index = 0;
        else
            index++;
        return index;
    }

}
