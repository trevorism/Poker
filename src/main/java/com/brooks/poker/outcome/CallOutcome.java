package com.brooks.poker.outcome;

import com.brooks.poker.game.GameActions;
import com.brooks.poker.game.data.GameState;
import com.brooks.poker.player.Player;

/**
 * @author Trevor
 * 
 */
public class CallOutcome implements BettingOutcome{

    protected CallOutcome(){
    }

    @Override
    public void modifyGameState(GameState gameState, Player player){
        int betAmount = getBetAmount(gameState, player);        
        player.requestBet(betAmount);
        GameActions.updateCurrentBet(gameState.getPots(), player.getPendingBet());

        if (player.isAllIn()){
            gameState.getPots().insertSubpot(player);
        }

    }

    @Override
    public int getBetAmount(GameState gameState, Player player){
        return gameState.getPots().getCurrentBet() - player.getPendingBet();
    }

}
