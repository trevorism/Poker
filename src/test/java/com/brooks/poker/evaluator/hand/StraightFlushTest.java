package com.brooks.poker.evaluator.hand;

import java.util.ArrayList;
import java.util.List;

import com.brooks.poker.cards.Card;
import com.brooks.poker.cards.Card.Suit;
import com.brooks.poker.cards.Card.Value;
import com.brooks.poker.cards.HandValue.HandValueType;
import com.brooks.poker.evaluator.HandValueEvaluator;

/**
 * @author Trevor
 * 
 */
public class StraightFlushTest extends HandValueEvaluatorBaseTest{

    @Override
    protected HandValueEvaluator instantiateEvaluator(){
        return new StraightFlush();
    }

    @Override
    protected HandValueType handValueType(){
        return HandValueType.STRAIGHT_FLUSH;
    }

    @Override
    protected List<Card> createValidHand(){
        List<Card> cards = new ArrayList<>();

        cards.add(new Card(Suit.CLUBS, Value.SIX));
        cards.add(new Card(Suit.CLUBS, Value.TWO));
        cards.add(new Card(Suit.HEARTS, Value.SEVEN));
        cards.add(new Card(Suit.CLUBS, Value.FIVE));
        cards.add(new Card(Suit.CLUBS, Value.FOUR));
        cards.add(new Card(Suit.CLUBS, Value.THREE));

        return cards;
    }

    @Override
    protected List<Value> createTieBreakerForValidHand(){
        List<Value> value = new ArrayList<>();
        value.add(Value.SIX);
        return value;
    }

    @Override
    protected List<Card> createInvalidHand(){
        List<Card> cards = new ArrayList<>();

        cards.add(new Card(Suit.CLUBS, Value.SEVEN));
        cards.add(new Card(Suit.SPADES, Value.KING));
        cards.add(new Card(Suit.SPADES, Value.QUEEN));
        cards.add(new Card(Suit.SPADES, Value.JACK));
        cards.add(new Card(Suit.HEARTS, Value.TEN));
        cards.add(new Card(Suit.SPADES, Value.FOUR));
        cards.add(new Card(Suit.DIAMONDS, Value.JACK));

        return cards;
    }
}
