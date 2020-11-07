package com.brooks.poker.evaluator.hand;

import com.brooks.poker.cards.Card;
import com.brooks.poker.cards.Card.Value;
import com.brooks.poker.cards.HandValue;
import com.brooks.poker.cards.HandValue.HandValueType;
import com.brooks.poker.evaluator.HandValueEvaluator;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Trevor
 */
public class FullHouse extends HandValueEvaluator {

    @Override
    public HandValue evaluate(List<Card> cards) {
        HandValue tripsHandValue = new ThreeOfAKind().calculateHandValue(cards);
        HandValue twoPairHandValue = new TwoPair().calculateHandValue(cards);

        if (!tripsHandValue.isValidHandValue())
            return HandValue.NOT_VALID_HAND;
        if (!twoPairHandValue.isValidHandValue())
            return HandValue.NOT_VALID_HAND;

        return evaluate(tripsHandValue, twoPairHandValue);
    }

    private HandValue evaluate(HandValue tripsHandValue, HandValue twoPairHandValue) {
        List<Value> tieBreaker = new LinkedList<>();
        tieBreaker.add(tripsHandValue.getTieBreakerAt(0));

        Value firstTieBreakerOfTwoPair = twoPairHandValue.getTieBreakerAt(0);

        if (!tieBreaker.contains(firstTieBreakerOfTwoPair)) {
            tieBreaker.add(firstTieBreakerOfTwoPair);
        } else {
            tieBreaker.add(twoPairHandValue.getTieBreakerAt(1));
        }

        return new HandValue(handValueType(), tieBreaker);
    }

    @Override
    protected HandValueType handValueType() {
        return HandValueType.FULL_HOUSE;
    }

}
