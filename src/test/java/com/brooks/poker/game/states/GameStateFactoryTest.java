package com.brooks.poker.game.states;

import java.util.List;

import org.junit.Test;

import com.brooks.poker.game.data.GamePhase;
import com.brooks.poker.game.data.GameState;
import com.brooks.poker.game.progress.GameStateFactory;
import com.brooks.poker.player.Player;
import com.brooks.poker.util.PlayerTestSetups;
import com.brooks.poker.util.PokerTestUtils;

import static org.junit.Assert.assertNotNull;

/**
 * @author Trevor
 *
 */
public class GameStateFactoryTest{

    @Test
    public void testInstantiated(){
        List<Player> players = PlayerTestSetups.allCallingPlayers();
        GameState gameState = PokerTestUtils.getDefaultGameState(players);
        GameStateFactory factory = new GameStateFactory(gameState);
        
        for(GamePhase phase : GamePhase.values()){
            assertNotNull(factory.getStateHandler(phase));
        }
    }
}
