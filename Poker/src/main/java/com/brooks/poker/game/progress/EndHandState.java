/**
 * 
 */
package com.brooks.poker.game.progress;

import com.brooks.poker.game.data.GamePhase;
import com.brooks.poker.game.data.GameState;
import com.brooks.poker.game.data.Table;
import com.brooks.poker.game.data.pot.Pots;
import com.brooks.poker.player.Player;

/**
 * @author Trevor
 * 
 */
public class EndHandState extends GameProgressHandler{

    public EndHandState(GameState gameState){
        super(gameState);
    }

    @Override
    public void handleState(){
        Pots pots = gameState.getPots();
        pots.awardWinners();
        removePlayersWithNoChips();
    }

    @Override
    public GamePhase getNextPhase(){
        Table table = gameState.getTable();
        if (table.getAllPlayers().size() <= 1){
            return GamePhase.END_GAME;
        }

        return GamePhase.BEGIN_HAND;
    }

    private void removePlayersWithNoChips(){
        Table table = gameState.getTable();
        for (Player player : table.getAllPlayers()){
            if (player.getChipCount() <= 0){
                table.removePlayer(player);
            }
        }
    }

}
