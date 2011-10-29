package com.brooks.poker.util;

import java.util.List;

import com.brooks.poker.game.data.BlindsAnte;
import com.brooks.poker.game.data.GameState;
import com.brooks.poker.game.data.Table;
import com.brooks.poker.player.Player;

import static org.junit.Assert.assertEquals;

/**
 * @author Trevor
 * 
 */
public final class PokerTestUtils{

    private PokerTestUtils(){}
    
    public static GameState getDefaultGameState(List<Player> players){
        GameState gameState = new GameState();

        BlindsAnte blindsAnte = gameState.getBlindsAnte();
        blindsAnte.ante = 0;
        blindsAnte.smallBlind = 25;
        blindsAnte.bigBlind = 35;

        Table table = gameState.getTable();
        for(Player player : players){
            table.joinTable(player);
        }

        gameState.getTable().setDealer(players.get(0));
        gameState.getPots().reset(table.getAllPlayers());

        return gameState;
    }
    
    public static void assertPlayerChipCount(Player player, int expectedCount){
        assertEquals(expectedCount, player.getChipCount());
        assertEquals(0, player.getPendingBet());
    }

    
}
