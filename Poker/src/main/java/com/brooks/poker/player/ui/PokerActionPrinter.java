package com.brooks.poker.player.ui;

import java.util.Collection;

import com.brooks.poker.game.data.GameState;
import com.brooks.poker.game.data.Table;
import com.brooks.poker.player.Player;

/**
 * @author Trevor
 * 
 */
public class PokerActionPrinter{

    public static void printGameState(GameState gameState){
        System.out.println(GameStatePrinter.createString(gameState));
    }

    public static void printLosers(Collection<Player> players)
    {
        StringBuilder builder = new StringBuilder();
        for(Player p : players){
            builder.append(p);
            builder.append(" loses with hand ");
            
            builder.append(HandPrinter.createSortedHandString(p.getHand()));
            builder.append("\n");
        }
        System.out.println(builder.toString());
    }
    public static void printWinner(Table table, Player player, int amount){
        StringBuilder builder = new StringBuilder();
        
        builder.append("Congratulations. ");
        builder.append(player);
        builder.append(" wins ");
        builder.append(amount);
        builder.append("\n");
        builder.append(HandPrinter.createSortedHandString(player.getHand()));

        System.out.println(builder.toString());
    }

    
    
    public static void printPrivatePlayer(Player player){
        StringBuilder builder = new StringBuilder();
        builder.append(player);
        builder.append(" ");
        builder.append(HandPrinter.createHoleCardsString(player.getHand()));
        builder.append("\n");

        System.out.println(builder.toString());
    }
}
