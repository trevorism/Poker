package com.brooks.poker.outcome;

import com.brooks.poker.game.data.GameState;
import com.brooks.poker.player.Player;
import com.brooks.poker.util.PlayerTestSetups;
import com.brooks.poker.util.PokerTestUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class FixedBetOutcomeTest{

    private GameState gameState;
    private RaiseOutcome fixedBet;
    private Player player;

    public static final int BET_AMOUNT = 50;

    @Before
    public void setUp(){
        List<Player> players = PlayerTestSetups.allCallingPlayers();
        gameState = PokerTestUtils.getDefaultGameState(players);
        fixedBet = new RaiseOutcome(BET_AMOUNT);
        player = PlayerTestSetups.getPlayer1();
    }

    @Test
    public void testModifyGameState(){
        assertNoBetsOccured();
        fixedBet.modifyGameState(gameState, player);
        assertBetsOccured();
    }

    private void assertNoBetsOccured(){
        assertEquals(0, player.getPendingBet());
        assertEquals(0, gameState.getPots().getCurrentBet());
    }

    private void assertBetsOccured(){
        assertEquals(BET_AMOUNT, player.getPendingBet());
        assertEquals(BET_AMOUNT, gameState.getPots().getCurrentBet());
    }
    
}
