package com.brooks.poker.game.states;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.brooks.poker.cards.Card;
import com.brooks.poker.game.data.GamePhase;
import com.brooks.poker.game.data.GameState;
import com.brooks.poker.game.progress.FlopState;
import com.brooks.poker.player.Player;
import com.brooks.poker.util.PokerTestUtils;

import static com.brooks.poker.util.PlayerTestSetups.programmaticPlayers;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class FlopStateTest{

    private GameState gameState;
    private FlopState flopState;

    @Before
    public void setUp(){
        List<Player> players = programmaticPlayers();
        gameState = PokerTestUtils.getDefaultGameState(players);
        flopState = new FlopState(gameState);
    }

    @Test
    public void testHandleState(){
        flopState.handleState();
        
        List<Card> cards = gameState.getCommunityCards().getCards();
        assertEquals(3, cards.size());
        
        for(Card card : cards){
            assertFalse(card.isNullCard());
        }
    }

    @Test
    public void testGetNextPhase(){
        assertEquals(GamePhase.FLOP_BET, flopState.getNextPhase());
    }

}
