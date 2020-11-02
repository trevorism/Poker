package com.brooks.poker.player.action;

import com.brooks.poker.game.data.GameState;
import com.brooks.poker.outcome.BettingOutcome;
import com.brooks.poker.outcome.BettingOutcomeFactory;
import com.brooks.poker.player.Player;

/**
 * @author Trevor
 * 
 */
public final class NullPlayerAction implements PlayerAction{

    public static final NullPlayerAction INSTANCE = new NullPlayerAction();

    private NullPlayerAction(){
    }

    @Override
    public BettingOutcome getBettingOutcome(GameState gameState, Player player){
        return BettingOutcomeFactory.createFoldOutcome();
    }

}
