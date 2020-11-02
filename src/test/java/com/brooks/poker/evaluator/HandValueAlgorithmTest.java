package com.brooks.poker.evaluator;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.brooks.poker.cards.Card;
import com.brooks.poker.cards.Card.Suit;
import com.brooks.poker.cards.Card.Value;
import com.brooks.poker.cards.HandValue;
import com.brooks.poker.cards.HandValue.HandValueType;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HandValueAlgorithmTest{

    @Test
    public void testMultipleHandValueAlgorithmCalls(){        
        List<Card> cards = createHighCardHand();
        assertHandValueIsHighCard(cards);       
        
        cards = addACardToMakeItAPair(cards);        
        assertHandValueIsPair(cards);
        
        cards = addACardToKeepItAPair(cards);        
        assertHandValueIsPair(cards);        
    }

    private List<Card> createHighCardHand(){
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(Suit.SPADES, Value.THREE));
        cards.add(new Card(Suit.HEARTS, Value.FOUR));
        cards.add(new Card(Suit.DIAMONDS, Value.SIX));
        cards.add(new Card(Suit.CLUBS, Value.EIGHT));
        cards.add(new Card(Suit.CLUBS, Value.TEN));
        return cards;
    }

    private List<Card> addACardToMakeItAPair(List<Card> cards){
        Card card = new Card(Suit.HEARTS, Value.TEN);
        cards.add(card);
        return cards;
    }
    
    private List<Card> addACardToKeepItAPair(List<Card> cards){
        Card card = new Card(Suit.HEARTS, Value.KING);
        cards.add(card);
        return cards;
    }

    private void assertHandValueIsPair(List<Card> cards){
        HandValue value = HandValueAlgorithm.getInstance().calculateHandValue(cards);
        assertTrue(value.isValidHandValue());
        assertEquals(HandValueType.PAIR, value.getType());
    }

    private void assertHandValueIsHighCard(List<Card> cards){
        HandValue value = HandValueAlgorithm.getInstance().calculateHandValue(cards);
        assertTrue(value.isValidHandValue());
        assertEquals(HandValueType.HIGH_CARD, value.getType());
        
    }

}
