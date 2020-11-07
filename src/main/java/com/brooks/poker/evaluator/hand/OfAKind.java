package com.brooks.poker.evaluator.hand;

import com.brooks.poker.cards.Card;
import com.brooks.poker.cards.Card.Value;
import com.brooks.poker.cards.CardUtils;
import com.brooks.poker.cards.Hand;
import com.brooks.poker.cards.HandValue;
import com.brooks.poker.evaluator.HandValueEvaluator;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Trevor
 */
public abstract class OfAKind extends HandValueEvaluator {

    @Override
    protected HandValue evaluate(List<Card> cards) {
        Value maxValue = CardUtils.findMaximum(cards);

        while (maxValue.ordinal() > Value.NULL.ordinal()) {
            if (CardUtils.countValues(cards, maxValue) == ofAKindCount()) {
                List<Value> tieBreaker = calculateTieBreaker(cards, maxValue);
                return new HandValue(handValueType(), tieBreaker);
            }
            maxValue = CardUtils.findMaximum(cards, maxValue.ordinal());
        }
        return HandValue.NOT_VALID_HAND;
    }

    private List<Value> calculateTieBreaker(List<Card> cards, Value ofAKindValue) {
        List<Value> tieBreaker = new LinkedList<>();
        tieBreaker.add(ofAKindValue);

        int finalTieBreakerSize = (Hand.HAND_SIZE + 1) - ofAKindCount();
        Value maxCardValue = CardUtils.findMaximum(cards);

        while (tieBreaker.size() != finalTieBreakerSize) {
            if (!tieBreaker.contains(maxCardValue)) {
                tieBreaker.add(maxCardValue);
            }

            maxCardValue = CardUtils.findMaximum(cards, maxCardValue.ordinal());
        }

        return tieBreaker;
    }

    protected abstract int ofAKindCount();
}
