package com.brooks.poker.evaluator;

import com.brooks.poker.cards.Card;
import com.brooks.poker.cards.Hand;
import com.brooks.poker.cards.HandValue;
import com.brooks.poker.evaluator.hand.*;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Trevor
 */
public class HandValueAlgorithm {

    private static final HandValueAlgorithm INSTANCE = new HandValueAlgorithm();

    public static final HandValueAlgorithm getInstance() {
        return INSTANCE;
    }

    private final List<HandValueEvaluator> orderedHandEvaluators;

    protected HandValueAlgorithm() {
        orderedHandEvaluators = new LinkedList<>();
        orderedHandEvaluators.add(new StraightFlush());
        orderedHandEvaluators.add(new FourOfAKind());
        orderedHandEvaluators.add(new FullHouse());
        orderedHandEvaluators.add(new Flush());
        orderedHandEvaluators.add(new Straight());
        orderedHandEvaluators.add(new ThreeOfAKind());
        orderedHandEvaluators.add(new TwoPair());
        orderedHandEvaluators.add(new Pair());
        orderedHandEvaluators.add(new HighCard());
    }

    public HandValue calculateHandValue(List<Card> cards) {
        if (cards.size() < Hand.HAND_SIZE) {
            return HandValue.NOT_VALID_HAND;
        }

        for (HandValueEvaluator evaluator : orderedHandEvaluators) {
            HandValue handValue = evaluator.calculateHandValue(cards);

            if (handValue.isValidHandValue()) {
                return handValue;
            }
        }
        return HandValue.NOT_VALID_HAND;
    }

}
