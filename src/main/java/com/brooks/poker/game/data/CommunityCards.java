package com.brooks.poker.game.data;

import com.brooks.poker.cards.Card;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Trevor
 */
public class CommunityCards {
    private final List<Card> cards = new LinkedList<>();

    public void reset() {
        cards.clear();
    }

    public void add(Card card) {
        cards.add(card);
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }

    public List<Card> getCards() {
        return new LinkedList<>(cards);
    }

}
