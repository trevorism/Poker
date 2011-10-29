package com.brooks.poker.player.ui;

import com.brooks.poker.command.PlayerCommand;
import com.brooks.poker.game.data.GameState;
import com.brooks.poker.game.data.Table;
import com.brooks.poker.player.Player;

/**
 * @author Trevor
 * 
 */
public class PlayerPrinterCommand implements PlayerCommand{

    private GameState gameState;
    private StringBuilder builder;

    public PlayerPrinterCommand(GameState gameState, StringBuilder builder){
        this.gameState = gameState;
        this.builder = builder;
    }

    @Override
    public void execute(Player player){
        builder.append(lastAction(player));
        builder.append(" ");
        builder.append(dealerIndicator(player));
        builder.append(" ");
        builder.append(player);
        builder.append(pendingBet(player));
        builder.append("\n");
    }

    private String pendingBet(Player player){
        if (player.getPendingBet() == 0)
            return "";

        return " Bet this round: " + player.getPendingBet();
    }

    private String lastAction(Player player){
        Table table = gameState.getTable();

        if (player.isAllIn()){
            return "All in";
        }
        if (table.isInactive(player)){
            return "Folded";
        }
        return "";
    }

    private String dealerIndicator(Player player){
        Player dealer = gameState.getTable().getDealer();
        if (player.equals(dealer)){
            return "*";
        }
        return "";
    }

}
