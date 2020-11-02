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
public class TwoPairTest extends HandValueEvaluatorBaseTest{

    @Override
    protected HandValueEvaluator instantiateEvaluator(){
        return new TwoPair();
    }

    @Override
    protected HandValueType handValueType(){
        return HandValueType.TWO_PAIR;
    }

    @Override
    protected List<Card> createValidHand(){
        List<Card> cards = new ArrayList<>();

        cards.add(new Card(Suit.CLUBS, Value.ACE));
        cards.add(new Card(Suit.SPADES, Value.JACK));
        cards.add(new Card(Suit.HEARTS, Value.FOUR));
        cards.add(new Card(Suit.SPADES, Value.FOUR));
        cards.add(new Card(Suit.DIAMONDS, Value.JACK));

        return cards;
    }

    @Override
    protected List<Value> createTieBreakerForValidHand(){
        List<Value> value = new ArrayList<>();
        value.add(Value.JACK);
        value.add(Value.FOUR);
        value.add(Value.ACE);

        return value;
    }

    @Override
    protected List<Card> createInvalidHand(){
        List<Card> cards = new ArrayList<>();

        cards.add(new Card(Suit.DIAMONDS, Value.TWO));
        cards.add(new Card(Suit.DIAMONDS, Value.ACE));
        cards.add(new Card(Suit.DIAMONDS, Value.SIX));
        cards.add(new Card(Suit.SPADES, Value.TEN));
        cards.add(new Card(Suit.CLUBS, Value.THREE));
        cards.add(new Card(Suit.SPADES, Value.KING));
        cards.add(new Card(Suit.CLUBS, Value.SEVEN));

        return cards;
    }

}
