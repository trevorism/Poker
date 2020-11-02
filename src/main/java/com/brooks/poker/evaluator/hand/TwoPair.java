package com.brooks.poker.evaluator.hand;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.brooks.poker.cards.Card;
import com.brooks.poker.cards.Card.Value;
import com.brooks.poker.cards.CardUtils;
import com.brooks.poker.cards.HandValue;
import com.brooks.poker.cards.HandValue.HandValueType;
import com.brooks.poker.evaluator.HandValueEvaluator;

/**
 * @author Trevor
 *
 */
public class TwoPair extends HandValueEvaluator {

    private static final int PAIR_AMOUNT = 2;
    private static final int PAIR_COUNT = 2;
    private static final int TIE_BREAKER_SIZE = 3;
    
    private List<Value> pairCount;
     
	@Override
	protected HandValue evaluate(List<Card> cards) {
	    pairCount = new ArrayList<>();
	    Value maxValue = CardUtils.findMaximum(cards);

        while (maxValue.ordinal() > Value.NULL.ordinal()){
            if (CardUtils.countValues(cards, maxValue) >= PAIR_AMOUNT){
                pairCount.add(maxValue);
            }
            maxValue = CardUtils.findMaximum(cards, maxValue.ordinal());
        }
        
        if(pairCount.size() >= PAIR_COUNT){
            List<Value> tieBreaker = calculateTieBreaker(cards);            
            return new HandValue(handValueType(), tieBreaker);
        }
        
        return HandValue.NOT_VALID_HAND;
	    
	}

    private List<Value> calculateTieBreaker(List<Card> cards){
        List<Value> tieBreaker = new LinkedList<>();
        tieBreaker.add(pairCount.get(0));
        tieBreaker.add(pairCount.get(1));
        
        Value maxCardValue = CardUtils.findMaximum(cards);

        while (tieBreaker.size() != TIE_BREAKER_SIZE){
            if (!tieBreaker.contains(maxCardValue)){
                tieBreaker.add(maxCardValue);
            }

            maxCardValue = CardUtils.findMaximum(cards, maxCardValue.ordinal());
        }
        return tieBreaker;
    }

    @Override
    protected HandValueType handValueType(){
        
        return HandValueType.TWO_PAIR;
    }
}
