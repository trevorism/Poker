package com.brooks.poker.game.data;

import com.brooks.poker.cards.CardUtils;
import com.brooks.poker.command.PlayerCommand;
import com.brooks.poker.player.Player;

import java.util.*;

/**
 * @author Trevor
 */

public class Table {

    private final List<Player> allPlayers = new ArrayList<>();
    private final Set<Player> foldedPlayers = new HashSet<>();
    private int dealerIndex = -1;

    public Player getNextActivePlayer(Player startPlayer) {
        Player player = getNextPlayer(startPlayer);

        while (foldedPlayers.contains(player)) {
            player = getNextPlayer(player);
            if (player.isNullPlayer()) {
                return Player.NOBODY;
            }
            if (player.equals(startPlayer)) {
                return startPlayer;
            }
        }
        return player;
    }

    public void makeNextPlayerDealer() {
        Player nextPlayer = getNextPlayer(getDealer());
        setDealer(nextPlayer);
    }

    public Player getDealer() {
        if (dealerIndex < 0 || dealerIndex >= allPlayers.size())
            dealerIndex = 0;

        return allPlayers.get(dealerIndex);
    }

    public void setDealer(Player player) {
        dealerIndex = allPlayers.indexOf(player);
    }

    public void joinTable(Player player) {
        allPlayers.add(player);
    }

    public void removePlayer(Player player) {
        allPlayers.remove(player);
    }

    public void makeInactive(Player player) {
        foldedPlayers.add(player);
    }

    public boolean isInactive(Player player) {
        return foldedPlayers.contains(player);
    }

    public void randomizeDealer() {
        Random random = new Random();
        dealerIndex = random.nextInt(allPlayers.size());
    }

    public void reset() {
        foldedPlayers.clear();
        for (Player player : getAllPlayers()) {
            player.reset();
        }
        if (dealerIndex == -1)
            randomizeDealer();
    }

    public Set<Player> getAllPlayers() {
        return new HashSet<>(allPlayers);
    }

    public List<Player> getSortedActivePlayers() {
        final List<Player> activePlayers = new LinkedList<>();

        Player firstActivePlayer = getNextActivePlayer(getDealer());
        executeOnEachActivePlayer(firstActivePlayer, activePlayers::add);

        return activePlayers;
    }

    public void executeOnEachActivePlayer(Player startPlayer, PlayerCommand command) {
        if (isInactive(startPlayer))
            startPlayer = getNextActivePlayer(startPlayer);
        Player firstPlayer = startPlayer;

        do {
            command.execute(startPlayer);
            startPlayer = getNextActivePlayer(startPlayer);

            if (startPlayer.isNullPlayer())
                break;

        } while (!firstPlayer.equals(startPlayer));
    }

    private Player getNextPlayer(Player player) {
        int playerIndex = allPlayers.indexOf(player);
        if (playerIndex == -1)
            return Player.NOBODY;

        int nextIndex = CardUtils.circularIncrement(playerIndex, allPlayers.size());
        return allPlayers.get(nextIndex);
    }

}
