package com.brooks.poker.game.data.pot;

import com.brooks.poker.player.Player;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Trevor
 */
public class Pot {

    private final Set<Player> eligiblePlayers;
    private int pot;
    private int amountOwed;

    Pot(Collection<Player> players) {
        this(players, 0);
    }

    Pot(Collection<Player> players, int amountOwed) {
        this.eligiblePlayers = new HashSet<>(players);
        this.pot = 0;
        this.amountOwed = amountOwed;
    }

    public int getPotAmount() {
        return pot;
    }

    public int getAmountOwed() {
        return amountOwed;
    }

    public void setAmountOwed(int amount) {
        this.amountOwed = amount;
    }

    public int getEligiblePlayerCount() {
        return getEligiblePlayers().size();
    }

    Set<Player> getEligiblePlayers() {
        return new HashSet<Player>(eligiblePlayers);
    }

    void placeBet(int bet) {
        pot += bet;
    }

    void removePlayerFromPot(Player player) {
        eligiblePlayers.remove(player);
    }

}
