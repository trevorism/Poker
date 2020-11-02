package com.brooks.poker.util;

import org.junit.Test;

import com.brooks.poker.cards.Card.Suit;
import com.brooks.poker.cards.Card.Value;
import com.brooks.poker.cards.HandValue.HandValueType;
import com.brooks.poker.game.data.GamePhase;
import com.brooks.poker.util.EnumPrinter;

import static org.junit.Assert.assertEquals;

public class EnumPrinterTest{

    @Test
    public void testConvert(){
        assertEquals("Three", EnumPrinter.convertCase(Value.THREE));
        assertEquals("Diamonds", EnumPrinter.convertCase(Suit.DIAMONDS));
        assertEquals("Four of a Kind", EnumPrinter.convertCase(HandValueType.FOUR_OF_A_KIND));
        assertEquals("Begin Hand", EnumPrinter.convertCase(GamePhase.BEGIN_HAND));
        assertEquals("Five of Clubs", EnumPrinter.convertCase("FIVE OF CLUBS"));        
    }

}
