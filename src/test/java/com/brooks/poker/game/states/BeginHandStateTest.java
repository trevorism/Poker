package com.brooks.poker.game.states;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.brooks.poker.cards.Card;
import com.brooks.poker.game.GameActions;
import com.brooks.poker.game.data.CommunityCards;
import com.brooks.poker.game.data.GamePhase;
import com.brooks.poker.game.data.GameState;
import com.brooks.poker.game.data.Table;
import com.brooks.poker.game.data.pot.Pot;
import com.brooks.poker.game.data.pot.Pots;
import com.brooks.poker.game.progress.BeginHandState;
import com.brooks.poker.player.Player;
import com.brooks.poker.util.PlayerTestSetups;
import com.brooks.poker.util.PokerTestUtils;

/**
 * @author Trevor
 * 
 */
public class BeginHandStateTest{

    private GameState gameState;
    private BeginHandState beginHandState;

    private Player p1;
    private Player p2;
    private Player p3;
    
    @Before
    public void setUp(){
        List<Player> players = PlayerTestSetups.differentActions();
        gameState = PokerTestUtils.getDefaultGameState(players);
        
        p1 = PlayerTestSetups.getPlayer1();
        p2 = PlayerTestSetups.getPlayer2();
        p3 = PlayerTestSetups.getPlayer3();
        
        beginHandState = new BeginHandState(gameState);
    }

    @Test
    public void testHandleState(){
        beginHandState.handleState();

        assertBettingIsReset();  
        assertTableIsReset();
        assertPotsAreReset();
        assertCommunityCardsReset();
    }   

    @Test
    public void testGetNextPhase(){
        assertGamePhaseIsFirstBet();
    }
    
    private void assertBettingIsReset(){
        assertEquals(gameState.getBlindsAnte().bigBlind, GameActions.getMinBet(gameState));
        assertEquals(0, gameState.getPots().getCurrentBet()); 
    }

    private void assertTableIsReset(){
        Table table = gameState.getTable();
        
        assertFalse(table.isInactive(p1));
        assertFalse(table.isInactive(p2));
        assertFalse(table.isInactive(p3));
    }

    private void assertPotsAreReset(){
        Pots pots = gameState.getPots();
        List<Pot> potList = pots.getPots();
        
        assertEquals(1, potList.size());
        
        Pot pot = potList.get(0);
        assertEquals(0, pot.getAmountOwed());
        assertEquals(0, pot.getPotAmount());
    }
    
    private void assertCommunityCardsReset(){
        CommunityCards commCards = gameState.getCommunityCards();
        List<Card> cards = commCards.getCards();
        
        assertEquals(0,cards.size());
    }    

    private void assertGamePhaseIsFirstBet(){
        GamePhase gamePhase = beginHandState.getNextPhase();
        assertEquals(GamePhase.FIRST_BET, gamePhase);
    }
}
