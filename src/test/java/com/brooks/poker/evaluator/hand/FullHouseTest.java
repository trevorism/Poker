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
public class FullHouseTest extends HandValueEvaluatorBaseTest{

    @Override
    protected HandValueEvaluator instantiateEvaluator(){
        return new FullHouse();
    }

    @Override
    protected HandValueType handValueType(){
        return HandValueType.FULL_HOUSE;
    }

    @Override
    protected List<Card> createValidHand(){
        List<Card> cards = new ArrayList<>();

        cards.add(new Card(Suit.CLUBS, Value.QUEEN));
        cards.add(new Card(Suit.SPADES, Value.QUEEN));
        cards.add(new Card(Suit.HEARTS, Value.NINE));
        cards.add(new Card(Suit.SPADES, Value.NINE));
        cards.add(new Card(Suit.DIAMONDS, Value.QUEEN));
        cards.add(new Card(Suit.DIAMONDS, Value.SEVEN));

        return cards;
    }

    @Override
    protected List<Value> createTieBreakerForValidHand(){
        List<Value> value = new ArrayList<>();
        value.add(Value.QUEEN);
        value.add(Value.NINE);
        return value;
    }

    @Override
    protected List<Card> createInvalidHand(){
        List<Card> cards = new ArrayList<>();

        cards.add(new Card(Suit.CLUBS, Value.ACE));
        cards.add(new Card(Suit.SPADES, Value.ACE));
        cards.add(new Card(Suit.HEARTS, Value.NINE));
        cards.add(new Card(Suit.SPADES, Value.NINE));
        cards.add(new Card(Suit.DIAMONDS, Value.SEVEN));
        cards.add(new Card(Suit.HEARTS, Value.SIX));

        return cards;
    }

}
