package com.brooks.poker.evaluator;

import java.util.List;

import com.brooks.poker.cards.Card;
import com.brooks.poker.cards.HandValue;
import com.brooks.poker.cards.HandValue.HandValueType;

/**
 * @author Trevor
 *
 */
public abstract class HandValueEvaluator {
	
    public HandValue calculateHandValue(List<Card> cards){
        if(cards.size() < 5){
            return HandValue.NOT_VALID_HAND;
        }
        return evaluate(cards);
    }
    
    protected abstract HandValue evaluate(List<Card> cards);
    protected abstract HandValueType handValueType();
}
