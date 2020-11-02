package com.brooks.poker.player.action;

import com.brooks.poker.game.data.GameState;
import com.brooks.poker.outcome.BettingOutcome;
import com.brooks.poker.player.Player;

/**
 * @author Trevor
 */
public interface PlayerAction{

    public BettingOutcome getBettingOutcome(GameState gameState, Player player);

}
