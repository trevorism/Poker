/**
 * 
 */
package com.brooks.poker.game.states;

import java.util.Collection;

import com.brooks.poker.game.data.GamePhase;
import com.brooks.poker.game.data.GameState;
import com.brooks.poker.game.data.Table;
import com.brooks.poker.game.data.pot.PotResult;
import com.brooks.poker.game.data.pot.PotWinner;
import com.brooks.poker.game.data.pot.Pots;
import com.brooks.poker.player.Player;
import com.brooks.poker.player.ui.PokerActionPrinter;

/**
 * @author Trevor
 * 
 */
public class EndHandState extends GameStateHandler{

    public EndHandState(GameState gameState){
        super(gameState);
    }

    @Override
    public void handleState(){
        Pots pots = gameState.getPots();
        PotResult potResult = pots.awardWinners();

        printLosers(potResult);
        printWinners(potResult);

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

    private void printLosers(PotResult potResult){
        Collection<Player> losers = gameState.getTable().getAllPlayers();
        losers.removeAll(potResult.getWinningPlayers());
        PokerActionPrinter.printLosers(losers);
    }

    private void printWinners(PotResult potResult){
        for (PotWinner potWinner : potResult.getPotWinners()){
            PokerActionPrinter.printWinner(gameState.getTable(), potWinner.player, potWinner.amount);
        }
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
