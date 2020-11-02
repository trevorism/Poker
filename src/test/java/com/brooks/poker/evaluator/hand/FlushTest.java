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
public class FlushTest extends HandValueEvaluatorBaseTest{

    @Override
    protected HandValueEvaluator instantiateEvaluator(){
        return new Flush();
    }

    @Override
    protected HandValueType handValueType(){
        return HandValueType.FLUSH;
    }

    @Override
    protected List<Card> createValidHand(){
        List<Card> cards = new ArrayList<>();

        cards.add(new Card(Suit.HEARTS, Value.ACE));
        cards.add(new Card(Suit.SPADES, Value.KING));
        cards.add(new Card(Suit.HEARTS, Value.SEVEN));
        cards.add(new Card(Suit.HEARTS, Value.FOUR));
        cards.add(new Card(Suit.HEARTS, Value.THREE));
        cards.add(new Card(Suit.HEARTS, Value.QUEEN));

        return cards;
    }

    @Override
    protected List<Value> createTieBreakerForValidHand(){
        List<Value> value = new ArrayList<>();
        value.add(Value.ACE);
        value.add(Value.QUEEN);
        value.add(Value.SEVEN);
        value.add(Value.FOUR);
        value.add(Value.THREE);
        return value;
    }

    @Override
    protected List<Card> createInvalidHand(){
        List<Card> cards = new ArrayList<>();

        cards.add(new Card(Suit.CLUBS, Value.ACE));
        cards.add(new Card(Suit.SPADES, Value.ACE));
        cards.add(new Card(Suit.HEARTS, Value.ACE));
        cards.add(new Card(Suit.SPADES, Value.NINE));
        cards.add(new Card(Suit.DIAMONDS, Value.NINE));
        cards.add(new Card(Suit.HEARTS, Value.NINE));

        return cards;
    }

}
