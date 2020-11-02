package com.brooks.poker.outcome;

import com.brooks.poker.game.data.GameState;
import com.brooks.poker.player.Player;

/**
 * @author Trevor
 *
 */
public class BlindsOutcome extends RaiseOutcome{

    public BlindsOutcome(int blind){
        super(blind);
    }
    
    public int getBetAmount(GameState gameState, Player player){
        return fixedBet;
    }
}
