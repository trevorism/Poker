package com.brooks.poker.game.states;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.brooks.poker.game.data.GamePhase;
import com.brooks.poker.game.data.GameState;
import com.brooks.poker.game.progress.EndHandState;
import com.brooks.poker.outcome.BettingOutcome;
import com.brooks.poker.outcome.BettingOutcomeFactory;
import com.brooks.poker.player.Player;
import com.brooks.poker.util.PlayerTestSetups;
import com.brooks.poker.util.PokerTestUtils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * @author Trevor
 *
 */
public class EndHandStateTest{

    private GameState gameState;
    private EndHandState endHandState;

    private Player p1;
    
    @Before
    public void setUp(){
        List<Player> players = PlayerTestSetups.differentActions();
        gameState = PokerTestUtils.getDefaultGameState(players);
        
        p1 = PlayerTestSetups.getPlayer1();
        
        endHandState = new EndHandState(gameState);
    }
    @Test
    public void testHandleState(){
        BettingOutcome allIn = BettingOutcomeFactory.createRaiseOutcome(PlayerTestSetups.PLAYER_ONE_CHIPS);
        allIn.modifyGameState(gameState, p1);
        
        endHandState.handleState();
        
        assertFalse(gameState.getTable().getAllPlayers().contains(p1));
    }

    @Test
    public void testGetNextPhase(){
        assertEquals(GamePhase.BEGIN_HAND, endHandState.getNextPhase());
    }

}
