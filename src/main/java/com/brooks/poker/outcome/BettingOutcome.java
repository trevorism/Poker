package com.brooks.poker.outcome;

import com.brooks.poker.game.data.GameState;
import com.brooks.poker.player.Player;

/**
 * @author Trevor
 */
public interface BettingOutcome{
    
    void modifyGameState(GameState gameState, Player player);

    int getBetAmount(GameState gameState, Player player);
}
