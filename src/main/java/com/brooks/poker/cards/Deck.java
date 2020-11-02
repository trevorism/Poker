/**
 * 
 */
package com.brooks.poker.cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.brooks.poker.cards.Card.Suit;
import com.brooks.poker.cards.Card.Value;

/**
 * @author Trevor
 * 
 */
public class Deck{

    private static final int DECK_SIZE = 52;

    private final List<Card> cards;
    private final List<Card> usedCards;

    public Deck(){

        cards = new ArrayList<>(DECK_SIZE);
        usedCards = new ArrayList<>();

        for (Suit suit : Suit.values()){
            for (Value value : Value.values()){
                if (value != Value.NULL){
                    cards.add(new Card(suit, value));
                }
            }
        }
    }

    public Card dealCard(){
        Card card = cards.remove(0);
        usedCards.add(card);
        return card;
    }

    public void reset(){
        cards.addAll(usedCards);
        usedCards.clear();
        Collections.shuffle(cards);
    }

}
