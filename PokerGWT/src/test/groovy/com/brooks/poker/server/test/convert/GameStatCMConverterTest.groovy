package com.brooks.poker.server.test.convert;

import org.junit.Test

import com.brooks.poker.cards.Card
import com.brooks.poker.cards.Card.Suit
import com.brooks.poker.cards.Card.Value
import com.brooks.poker.game.data.CommunityCards
import com.brooks.poker.server.convert.GameStateCMConverter

public class GameStatCMConverterTest{

    @Test
    public void testConvertCommunityCards(){
        CommunityCards cc = new CommunityCards();
        cc.cards = [new Card(Suit.CLUBS,Value.ACE), new Card(Suit.SPADES, Value.FIVE)];
        
        GameStateCMConverter conv = new GameStateCMConverter();
        List<Card> cards = conv.convertCards(cc);
        
        assert cards
        assert cards.size() == 2
        assert cards[0].toString() == "ACE_CLUBS"
        assert cards[1].toString() == "FIVE_SPADES"
    }
}
