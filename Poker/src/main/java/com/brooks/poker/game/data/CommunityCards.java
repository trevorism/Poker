package com.brooks.poker.game.data;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.brooks.poker.cards.Card;

/**
 * @author Trevor
 *
 */
public class CommunityCards{
    private List<Card> cards = new LinkedList<Card>();
    
    public void reset(){
        cards.clear();
    }
    
    public void add(Card card){
        cards.add(card);
    }
    
    public boolean isEmpty(){
        return cards.isEmpty();
    }
    
    public List<Card> getCards(){
        return new ArrayList<Card>(cards);
    }
    
}
