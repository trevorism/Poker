package com.brooks.poker.evaluator.hand;

import java.util.ArrayList;
import java.util.List;

import com.brooks.poker.cards.Card;
import com.brooks.poker.cards.Card.Suit;
import com.brooks.poker.cards.Card.Value;
import com.brooks.poker.cards.HandValue;
import com.brooks.poker.cards.HandValue.HandValueType;
import com.brooks.poker.evaluator.HandValueEvaluator;

public class ThreeOfAKindTest extends HandValueEvaluatorBaseTest{

    @Override
    protected HandValueEvaluator instantiateEvaluator(){
        return new ThreeOfAKind();
    }

    @Override
    protected HandValueType handValueType(){
        return HandValue.HandValueType.THREE_OF_A_KIND;
    }

    @Override
    protected List<Card> createValidHand(){
        List<Card> cards = new ArrayList<>();

        cards.add(new Card(Suit.CLUBS, Value.ACE));
        cards.add(new Card(Suit.SPADES, Value.JACK));
        cards.add(new Card(Suit.HEARTS, Value.JACK));
        cards.add(new Card(Suit.SPADES, Value.NINE));
        cards.add(new Card(Suit.DIAMONDS, Value.JACK));

        return cards;
    }

    @Override
    protected List<Value> createTieBreakerForValidHand(){
        List<Value> value = new ArrayList<>();
        value.add(Value.JACK);
        value.add(Value.ACE);
        value.add(Value.NINE);
        return value;
    }

    @Override
    protected List<Card> createInvalidHand(){
        List<Card> cards = new ArrayList<>();

        cards.add(new Card(Suit.CLUBS, Value.ACE));
        cards.add(new Card(Suit.SPADES, Value.QUEEN));
        cards.add(new Card(Suit.HEARTS, Value.ACE));
        cards.add(new Card(Suit.SPADES, Value.JACK));
        cards.add(new Card(Suit.DIAMONDS, Value.JACK));
        cards.add(new Card(Suit.HEARTS, Value.QUEEN));

        return cards;
    }
}
