package com.brooks.poker.evaluator;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.brooks.poker.cards.Card;
import com.brooks.poker.cards.Card.Suit;
import com.brooks.poker.cards.Card.Value;
import com.brooks.poker.cards.Hand;

/**
 * @author Trevor
 *
 */
public class HandValueComparisonTest {

	@Test
	public void compareHands(){
		Hand hand1 = createHand1();
		Hand hand2 = createHand2();
		
		assertTrue(hand1.compareTo(hand2) > 0);
	}
	
	
	private Hand createHand1(){
		Hand hand = new Hand();
		hand.addCard(new Card(Suit.SPADES, Value.ACE));
		hand.addCard(new Card(Suit.HEARTS, Value.JACK));
		hand.addCard(new Card(Suit.SPADES, Value.THREE));
		hand.addCard(new Card(Suit.DIAMONDS, Value.EIGHT));
		hand.addCard(new Card(Suit.HEARTS, Value.EIGHT));
		hand.addCard(new Card(Suit.SPADES, Value.FOUR));
		hand.addCard(new Card(Suit.DIAMONDS, Value.THREE));
		return hand;
	}
	
	private Hand createHand2(){
		Hand hand = new Hand();
		hand.addCard(new Card(Suit.HEARTS, Value.TWO));
		hand.addCard(new Card(Suit.HEARTS, Value.SIX));
		hand.addCard(new Card(Suit.SPADES, Value.THREE));
		hand.addCard(new Card(Suit.DIAMONDS, Value.EIGHT));
		hand.addCard(new Card(Suit.HEARTS, Value.EIGHT));
		hand.addCard(new Card(Suit.SPADES, Value.FOUR));
		hand.addCard(new Card(Suit.DIAMONDS, Value.THREE));
		return hand;
	}
	
}
