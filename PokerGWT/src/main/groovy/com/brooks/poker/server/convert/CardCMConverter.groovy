package com.brooks.poker.server.convert;

import com.brooks.poker.cards.Card
import com.brooks.poker.client.model.CardCM

/**
 * @author Trevor
 *
 */
public class CardCMConverter{

    public List<CardCM> convert(List<Card> cards){
        cards.collect{
            CardCM cm = new CardCM(it.getValue().toString(), it.getSuit().toString())
        }
    }
}
