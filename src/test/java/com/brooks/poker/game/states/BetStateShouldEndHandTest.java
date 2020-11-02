package com.brooks.poker.game.states;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.brooks.poker.game.data.GameState;
import com.brooks.poker.game.progress.TurnBetState;
import com.brooks.poker.outcome.RaiseOutcome;
import com.brooks.poker.outcome.BettingOutcomeFactory;
import com.brooks.poker.player.Player;
import com.brooks.poker.player.action.AlwaysCallPlayerAction;
import com.brooks.poker.player.action.AlwaysFoldPlayerAction;
import com.brooks.poker.util.PokerTestUtils;

import static com.brooks.poker.util.PlayerTestSetups.getPlayer1;
import static com.brooks.poker.util.PlayerTestSetups.getPlayer2;
import static com.brooks.poker.util.PlayerTestSetups.getPlayer3;
import static com.brooks.poker.util.PlayerTestSetups.programPlayerAction;
import static com.brooks.poker.util.PlayerTestSetups.programmaticPlayers;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BetStateShouldEndHandTest{

    private GameState gameState;
    private TurnBetState turnBetState;

    private Player p1;
    private Player p2;
    private Player p3;

    private static final int BET_AMOUNT = 110;

    @Before
    public void setUp(){
        List<Player> players = programmaticPlayers();
        gameState = PokerTestUtils.getDefaultGameState(players);

        p1 = getPlayer1();
        p2 = getPlayer2();
        p3 = getPlayer3();

        turnBetState = new TurnBetState(gameState);
    }
    
    
    @Test
    public void testShouldEndHandAllIn(){
        programPlayerAction(p1, new AlwaysCallPlayerAction());
        programPlayerAction(p2, new AlwaysCallPlayerAction());
        programPlayerAction(p3, new AlwaysCallPlayerAction());
                
        gameState.getTable().setDealer(p1);
        RaiseOutcome fixedBet = BettingOutcomeFactory.createRaiseOutcome(BET_AMOUNT);
        fixedBet.modifyGameState(gameState, p2);
        turnBetState.bettingRound(p3);
        
        assertFalse(turnBetState.onePlayerInThePot());
    }
    
    @Test
    public void testShouldEndHandFold(){
        programPlayerAction(p1, new AlwaysFoldPlayerAction());
        programPlayerAction(p2, new AlwaysCallPlayerAction());
        programPlayerAction(p3, new AlwaysFoldPlayerAction());
                
        gameState.getTable().setDealer(p1);
        RaiseOutcome fixedBet = BettingOutcomeFactory.createRaiseOutcome(BET_AMOUNT);
        fixedBet.modifyGameState(gameState, p2);
        turnBetState.bettingRound(p3);
        
        assertTrue(turnBetState.onePlayerInThePot());
    }
    
}
