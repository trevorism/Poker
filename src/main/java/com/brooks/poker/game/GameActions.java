package com.brooks.poker.game;

import com.brooks.poker.cards.Card;
import com.brooks.poker.cards.Deck;
import com.brooks.poker.game.data.GameState;
import com.brooks.poker.game.data.Table;
import com.brooks.poker.game.data.pot.Pot;
import com.brooks.poker.game.data.pot.Pots;
import com.brooks.poker.player.Player;

import java.util.Set;

public class GameActions {

    private static final int MAXIMUM_NUMBER_OF_PLAYERS = 20;
    private static final int MINIMUM_NUMBER_OF_PLAYERS = 2;

    private GameActions() {
    }

    public static void beginHand(GameState gameState) {
        gameState.getTable().reset();
        gameState.getDeck().reset();
        gameState.getCommunityCards().reset();
        gameState.getTable().makeNextPlayerDealer();
        gameState.getPots().reset(gameState.getTable().getAllPlayers());
    }

    public static Player getPlayerAfterDealer(GameState gameState) {
        Table table = gameState.getTable();

        Player dealer = table.getDealer();
        return table.getNextActivePlayer(dealer);
    }

    public static void dealCardToPlayer(Deck deck, Player player) {
        Card card = deck.dealCard();
        player.addCard(card);
    }

    public static void burnCard(Deck deck) {
        deck.dealCard();
    }

    public static void dealCommunityCard(GameState gameState) {
        Card dealtCard = gameState.getDeck().dealCard();
        gameState.getCommunityCards().add(dealtCard);
        for (Player p : gameState.getTable().getSortedActivePlayers()) {
            p.addCard(dealtCard);
        }
    }

    public static void updateCurrentBet(Pots pots, int pendingBet) {
        pots.updateAmountOwed(pendingBet);
    }

    public static void endBettingRound(GameState gameState) {
        gameState.getPots().putPendingBetsIntoPots(gameState.getTable().getAllPlayers());
    }

    public static int getMinBet(GameState gameState) {
        int minBet = gameState.getPots().getCurrentBet() + gameState.getBlindsAnte().bigBlind;
        if (minBet < gameState.getBlindsAnte().bigBlind) {
            minBet = gameState.getBlindsAnte().bigBlind;
        }
        return minBet;
    }

    public static boolean invalid(GameState gameState) {
        Set<Player> players = gameState.getTable().getAllPlayers();
        if (players == null)
            return true;
        if (players.size() < MINIMUM_NUMBER_OF_PLAYERS)
            return true;
        return players.size() > MAXIMUM_NUMBER_OF_PLAYERS;
    }


}
