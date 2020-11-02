package com.brooks.poker.evaluator.hand;

import java.util.LinkedList;
import java.util.List;

import com.brooks.poker.cards.Card;
import com.brooks.poker.cards.Card.Suit;
import com.brooks.poker.cards.Card.Value;
import com.brooks.poker.cards.CardUtils;
import com.brooks.poker.cards.Hand;
import com.brooks.poker.cards.HandValue;
import com.brooks.poker.cards.HandValue.HandValueType;

/**
 * @author Trevor
 * 
 */
public class StraightFlush extends Straight{

    @Override
    protected HandValue evaluate(List<Card> cards){
        Value maxValue = CardUtils.findMaximum(cards);

        while (!impossibleStraight(maxValue)){
            for (Suit suit : CardUtils.findSuitsForValue(cards, maxValue)){
                HandValue handValue = calculateStraightFlush(cards, maxValue, suit);
                if (handValue.isValidHandValue()){
                    return handValue;
                }
            }
            maxValue = CardUtils.findMaximum(cards, maxValue.ordinal());
        }
        return HandValue.NOT_VALID_HAND;

    }

    private HandValue calculateStraightFlush(List<Card> cards, Value maxValue, Suit suit){
        List<Value> tieBreaker = new LinkedList<>();
        tieBreaker.add(maxValue);

        for (int i = 1; i < Hand.HAND_SIZE; i++){
            if (fiveToAceSpecialCase(maxValue, i)){
                return handValueWithAceAsOne(cards, tieBreaker, suit);
            }

            Value previousValue = valueAt(maxValue.ordinal() - i);
            Card card = new Card(suit, previousValue);
            card = CardUtils.findCard(cards, card);

            if (card.isNullCard()){
                return HandValue.NOT_VALID_HAND;
            }
        }

        return new HandValue(handValueType(), tieBreaker);
    }

    private HandValue handValueWithAceAsOne(List<Card> cards, List<Value> tieBreaker, Suit suit){
        Card card = new Card(suit, Value.ACE);
        card = CardUtils.findCard(cards, card);
        if (card.isNullCard()){
            return HandValue.NOT_VALID_HAND;
        }

        return new HandValue(handValueType(), tieBreaker);
    }

    @Override
    protected HandValueType handValueType(){

        return HandValueType.STRAIGHT_FLUSH;
    }
}
