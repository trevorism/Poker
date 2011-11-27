package com.brooks.poker.outcome;

import com.brooks.poker.game.data.GameState;
import com.brooks.poker.player.Player;

/**
 * @author Trevor
 */
public interface BettingOutcome{
    
    public void modifyGameState(GameState gameState, Player player);

    public int getBetAmount(GameState gameState, Player player);
}
