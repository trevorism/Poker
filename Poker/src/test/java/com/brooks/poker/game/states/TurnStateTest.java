package com.brooks.poker.game.states;

import java.util.List;

import org.junit.Test;

import com.brooks.poker.game.data.GamePhase;
import com.brooks.poker.game.data.GameState;
import com.brooks.poker.game.progress.TurnState;
import com.brooks.poker.player.Player;
import com.brooks.poker.util.PlayerTestSetups;
import com.brooks.poker.util.PokerTestUtils;

import static org.junit.Assert.assertEquals;

public class TurnStateTest{

    @Test
    public void testHandleState(){
        List<Player> players = PlayerTestSetups.allCallingPlayers();
        GameState gameState = PokerTestUtils.getDefaultGameState(players);
        
        assertEquals(0, gameState.getCommunityCards().getCards().size());
        
        TurnState turnState = new TurnState(gameState);
        turnState.handleState();

        assertEquals(1, gameState.getCommunityCards().getCards().size());
    }

    @Test
    public void testGetNextPhase(){
        TurnState turnState = new TurnState(PokerTestUtils.getDefaultGameState(PlayerTestSetups.allCallingPlayers()));
        assertEquals(GamePhase.TURN_BET, turnState.getNextPhase());
    }

}
