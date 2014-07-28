 package com.brooks.poker.evaluator.hand;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.brooks.poker.cards.Card;
import com.brooks.poker.cards.Card.Suit;
import com.brooks.poker.cards.Card.Value;
import com.brooks.poker.cards.CardUtils;
import com.brooks.poker.cards.Hand;
import com.brooks.poker.cards.HandValue;
import com.brooks.poker.cards.HandValue.HandValueType;
import com.brooks.poker.evaluator.HandValueEvaluator;

/**
 * @author Trevor
 *
 */
public class Flush extends HandValueEvaluator {

    private List<HandValue> suitHandValues;
    
	@Override
	public HandValue evaluate(List<Card> cards) {
	    suitHandValues = new LinkedList<HandValue>();
	    
	    for(Suit suit : Suit.values()){
	        suitHandValues.add(calculateForSuit(cards, suit));	    
	    }
	    
	    return Collections.max(suitHandValues);
		
	}

    private HandValue calculateForSuit(List<Card> cards, Suit suit){
        
        int count = CardUtils.countSuits(cards, suit);
        
        if(count < Hand.HAND_SIZE){
            return HandValue.NOT_VALID_HAND;
        }
        
        return new HandValue(handValueType(), calculateTieBreaker(cards, suit));
        
    }
   
    private List<Value> calculateTieBreaker(List<Card> cards, Suit suit){
        List<Value> tieBreaker = new LinkedList<Value>();
        
        Value maxValue = CardUtils.findMaximum(cards);
        
        while (tieBreaker.size() != Hand.HAND_SIZE){
            Card card = CardUtils.findCard(cards, new Card(suit,maxValue));            
            if(card.isNullCard()){
            	maxValue = CardUtils.findMaximum(cards, maxValue.ordinal());
            	continue;
            }

            tieBreaker.add(card.getValue());
            maxValue = CardUtils.findMaximum(cards, maxValue.ordinal());
        }
        
        return tieBreaker;
    }

    @Override
    protected HandValueType handValueType(){
        
        return HandValueType.FLUSH;
    }

}
