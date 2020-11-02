package com.brooks.poker.player.action;

import com.brooks.poker.game.data.GameState;
import com.brooks.poker.outcome.BettingOutcome;
import com.brooks.poker.outcome.BettingOutcomeFactory;
import com.brooks.poker.player.Player;

/**
 * @author Trevor
 * 
 */
public class AlwaysFoldPlayerAction implements PlayerAction{

    @Override
    public BettingOutcome getBettingOutcome(GameState gameState, Player player){
        return BettingOutcomeFactory.createFoldOutcome();
    }

}
