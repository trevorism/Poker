package com.brooks.poker.evaluator.hand;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.brooks.poker.cards.Card;
import com.brooks.poker.cards.Card.Value;
import com.brooks.poker.cards.HandValue;
import com.brooks.poker.cards.HandValue.HandValueType;
import com.brooks.poker.evaluator.HandValueEvaluator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Trevor
 * 
 */
public abstract class HandValueEvaluatorBaseTest{

    protected HandValueEvaluator evaluator;

    @Before
    public void setup(){
        evaluator = instantiateEvaluator();
    }

    @Test
    public void testEvaluate(){
        assertHandValueIsValid();
        assertTieBreakerWorks();
    }

    @Test
    public void testEvaluateWithInvalidHand(){
        List<Card> cards = createInvalidHand();
        HandValue handValue = evaluator.calculateHandValue(cards);
        assertFalse(handValue.isValidHandValue());
    }

    private void assertHandValueIsValid(){
        HandValue handValue = getValidHandValue();
        assertTrue(handValue.isValidHandValue());
    }

    private void assertTieBreakerWorks(){
        HandValue handValue = getValidHandValue();
        HandValue testHandValue = new HandValue(handValueType(), createTieBreakerForValidHand());
        assertEquals(testHandValue, handValue);
    }

    private HandValue getValidHandValue(){
        List<Card> cards = createValidHand();
        return evaluator.calculateHandValue(cards);
    }

    protected abstract HandValueEvaluator instantiateEvaluator();

    protected abstract HandValueType handValueType();

    protected abstract List<Card> createValidHand();

    protected abstract List<Value> createTieBreakerForValidHand();

    protected abstract List<Card> createInvalidHand();

}
