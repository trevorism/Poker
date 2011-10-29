/**
 * 
 */
package com.brooks.poker.cards;

import java.util.ArrayList;
import java.util.List;

import com.brooks.poker.evaluator.HandValueAlgorithm;

/**
 * Takes N cards, converts to the best 5 card hand.
 * @author Trevor
 */
public class Hand implements Comparable<Hand>{

    public static final int HAND_SIZE = 5;

    private List<Card> cards;
    private HandValue handValue;

    public Hand(){
        this(new ArrayList<Card>());
    }

    public Hand(List<Card> cards){
        this.cards = cards;
        this.handValue = HandValue.NOT_VALID_HAND;
    }

    public void addCard(Card c){
        cards.add(c);
        calculateHandValue();
    }

    public void reset(){
        cards.clear();
        handValue = HandValue.NOT_VALID_HAND;
    }

    public HandValue getHandValue(){
        return handValue;
    }

    public List<Card> getCards(){
        return new ArrayList<Card>(cards);
    }
    
    public int compareTo(Hand hand){
        return getHandValue().compareTo(hand.getHandValue());
    }

    private void calculateHandValue(){
        HandValueAlgorithm algorithm = HandValueAlgorithm.getInstance();
        handValue = algorithm.calculateHandValue(cards);
    }
    
    @Override
    public int hashCode(){
        final int prime = 31;
        int result = 1;
        result = prime * result + ((handValue == null) ? 0 : handValue.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Hand other = (Hand) obj;
        if (handValue == null){
            if (other.handValue != null)
                return false;
        }
        else if (!handValue.equals(other.handValue))
            return false;
        return true;
    }
}
