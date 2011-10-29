package com.brooks.poker.player.ui;

import java.util.Collections;
import java.util.List;

import com.brooks.poker.cards.Card;
import com.brooks.poker.cards.Hand;
import com.brooks.poker.cards.HandValue;

/**
 * @author Trevor
 * 
 */
public class HandPrinter{

    public static String createHoleCardsString(Hand hand){
        List<Card> cards = hand.getCards();
        if (cards.size() < 2)
            return "";

        Card card1 = cards.get(0);
        Card card2 = cards.get(1);

        StringBuilder builder = new StringBuilder();
        builder.append("(");
        builder.append(card1);
        builder.append(", ");
        builder.append(card2);
        builder.append(")");

        return builder.toString();
    }

    public static String createSortedHandString(Hand hand){
        HandValue handValue = hand.getHandValue();
        List<Card> cards = hand.getCards();
        Collections.sort(cards);
        Collections.reverse(cards);
        
        StringBuilder builder = new StringBuilder();
        if (handValue != HandValue.NOT_VALID_HAND){
            builder.append(EnumPrinter.convertCase(handValue.getType()));
            builder.append(": ");
        }

        builder.append(createCardsString(cards));

        return builder.toString();
    }
    
    public static String createCardsString(List<Card> cards){
        StringBuilder builder = new StringBuilder();
        for (int i = 0, n = cards.size(); i < n; i++){
            builder.append(cards.get(i));
            if (i + 1 != n)
                builder.append(", ");
        }
        return builder.toString();
    }
}
