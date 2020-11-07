package com.brooks.poker.evaluator.hand;

import com.brooks.poker.cards.Card;
import com.brooks.poker.cards.Card.Value;
import com.brooks.poker.cards.CardUtils;
import com.brooks.poker.cards.Hand;
import com.brooks.poker.cards.HandValue;
import com.brooks.poker.cards.HandValue.HandValueType;
import com.brooks.poker.evaluator.HandValueEvaluator;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Trevor
 */
public class Straight extends HandValueEvaluator {

    @Override
    protected HandValue evaluate(List<Card> cards) {
        Value maxValue = CardUtils.findMaximum(cards);

        while (!impossibleStraight(maxValue)) {
            HandValue handValue = calculateStraight(cards, maxValue);
            if (handValue.isValidHandValue()) {
                return handValue;
            }

            maxValue = CardUtils.findMaximum(cards, maxValue.ordinal());
        }
        return HandValue.NOT_VALID_HAND;

    }

    private HandValue calculateStraight(List<Card> cards, Value maxValue) {
        List<Value> tieBreaker = new LinkedList<>();
        tieBreaker.add(maxValue);

        for (int i = 1; i < Hand.HAND_SIZE; i++) {
            if (fiveToAceSpecialCase(maxValue, i)) {
                return handValueWithAceAsOne(cards, tieBreaker);
            }

            Value previousValue = valueAt(maxValue.ordinal() - i);
            Card card = CardUtils.findCard(cards, previousValue);

            if (card.isNullCard()) {
                return HandValue.NOT_VALID_HAND;
            }
        }

        return new HandValue(handValueType(), tieBreaker);
    }

    private HandValue handValueWithAceAsOne(List<Card> cards, List<Value> tieBreaker) {
        Card card = CardUtils.findCard(cards, Value.ACE);
        if (card.isNullCard()) {
            return HandValue.NOT_VALID_HAND;
        }

        return new HandValue(handValueType(), tieBreaker);
    }

    protected boolean impossibleStraight(Value maxValue) {
        return maxValue.ordinal() - (Hand.HAND_SIZE - 1) < 0;
    }

    protected boolean fiveToAceSpecialCase(Value maxValue, int index) {
        return maxValue.ordinal() - index == 0;
    }

    protected Value valueAt(int ordinal) {
        return Value.values()[ordinal];
    }

    @Override
    protected HandValueType handValueType() {
        return HandValueType.STRAIGHT;
    }

}
