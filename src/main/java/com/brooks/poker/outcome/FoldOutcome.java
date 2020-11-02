package com.brooks.poker.outcome;

import com.brooks.poker.game.data.GameState;
import com.brooks.poker.game.data.Table;
import com.brooks.poker.game.data.pot.Pots;
import com.brooks.poker.player.Player;

/**
 * @author Trevor
 * 
 */
public class FoldOutcome implements BettingOutcome{

    protected FoldOutcome(){
    }

    @Override
    public void modifyGameState(GameState gameState, Player player){
        Table table = gameState.getTable();
        table.makeInactive(player);

        Pots pots = gameState.getPots();
        pots.fold(player);
    }

    @Override
    public int getBetAmount(GameState gameState, Player player){
        return 0;
    }

}
