package com.brooks.poker.util;

import java.util.List;

import com.brooks.poker.game.data.BlindsAnte;
import com.brooks.poker.game.data.GameState;
import com.brooks.poker.player.Player;

import static org.junit.Assert.assertEquals;

/**
 * @author Trevor
 * 
 */
public final class PokerTestUtils{

    private PokerTestUtils(){}
    
    public static GameState getDefaultGameState(List<Player> players){
        BlindsAnte blindsAnte = new BlindsAnte(35,25,0);
        GameState gameState = GameState.configureTournamentGameState(blindsAnte, players);
        
        gameState.getTable().setDealer(players.get(0));
        gameState.getPots().reset(gameState.getTable().getAllPlayers());

        return gameState;
    }
    
    public static void assertPlayerChipCount(Player player, int expectedCount){
        assertEquals(expectedCount, player.getChipCount());
        assertEquals(0, player.getPendingBet());
    }

    public static GameState getCrazyGameState(List<Player> players){
        BlindsAnte blindsAnte = new BlindsAnte(35,25,0);
        GameState gameState = GameState.configureTournamentGameState(blindsAnte, players);

        gameState.getTable().setDealer(players.get(2));
        gameState.getPots().reset(gameState.getTable().getAllPlayers());

        return gameState;
    }
    
}
