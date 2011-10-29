package com.brooks.poker.player.ui;

import com.brooks.poker.game.data.CommunityCards;
import com.brooks.poker.game.data.GameState;
import com.brooks.poker.game.data.Table;
import com.brooks.poker.game.data.pot.Pots;
import com.brooks.poker.player.Player;

/**
 * @author Trevor
 * 
 */
public class GameStatePrinter{

    public static String createString(GameState gameState){
        StringBuilder builder = new StringBuilder();

        String playersString = createPlayersString(gameState);
        String potsString = createPotsString(gameState.getPots());
        String communityCards = createCommunityCardsString(gameState.getCommunityCards());

        builder.append("-----------------------------------------------\n");
        builder.append("                   Game State                  \n");
        builder.append("\n");
        builder.append(playersString);
        builder.append("\n");
        builder.append(potsString);
        builder.append("\n");
        builder.append(communityCards);
        builder.append("\n");
        builder.append("-----------------------------------------------\n");

        return builder.toString();
    }

    private static String createPlayersString(GameState gameState){
        StringBuilder builder = new StringBuilder();
        Table table = gameState.getTable();
        builder.append("Players\n");
        Player firstPlayer = table.getSortedActivePlayers().get(0);
        table.executeOnEachActivePlayer(firstPlayer, new PlayerPrinterCommand(gameState, builder));
        return builder.toString();
    }

    private static String createPotsString(Pots pots){
        StringBuilder builder = new StringBuilder();
        builder.append("Pots\n");
        builder.append(PotsPrinter.createPotsString(pots));
        return builder.toString();
    }

    private static String createCommunityCardsString(CommunityCards communityCards){
        if (communityCards.isEmpty()){
            return "";
        }
        StringBuilder builder = new StringBuilder();
        builder.append("Community Cards\n");
        builder.append(HandPrinter.createCardsString(communityCards.getCards()));
        return builder.toString();
    }
}
