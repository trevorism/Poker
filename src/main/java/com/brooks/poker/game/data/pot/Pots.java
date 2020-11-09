package com.brooks.poker.game.data.pot;

import com.brooks.poker.player.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * @author Trevor
 */
public class Pots {
    private final List<Pot> pots = new ArrayList<Pot>();

    public void reset(Collection<Player> players) {
        pots.clear();
        pots.add(new Pot(players));
    }

    public void fold(Player player) {
        for (Pot pot : pots) {
            pot.removePlayerFromPot(player);
        }
    }

    public PotResult awardWinners() {
        PotResult result = new PotResult();
        for (int i = pots.size() - 1; i >= 0; i--) {
            Pot pot = pots.get(i);
            if (pot.getEligiblePlayerCount() == 0 && pot.getPotAmount() != 0 && i != 0) {
                Pot previousPot = pots.get(i - 1);
                previousPot.placeBet(pot.getPotAmount());
            } else {
                result.resolvePot(pot);
            }
        }
        return result;
    }

    public void putPendingBetsIntoPots(Collection<Player> players) {
        for (Pot pot : pots) {
            for (Player player : players) {
                int amountFromPlayer = player.addPendingBetToPot(pot.getAmountOwed());
                pot.placeBet(amountFromPlayer);
            }
            pot.setAmountOwed(0);
        }
    }

    public void insertSubpot(Player player) {
        int pendingBet = player.getPendingBet();
        for (Pot pot : pots) {
            if (pendingBet <= pot.getAmountOwed()) {
                splitPot(pot, player, pendingBet);
                return;
            } else {
                pendingBet = pendingBet - pot.getAmountOwed();
            }
        }
    }

    public void updateAmountOwed(int pendingBet) {
        if (pendingBet > getCurrentBet())
            recurseSetAmountOwed(pendingBet, 0);
    }

    public int getCurrentBet() {
        int currentBet = 0;
        for (Pot pot : pots) {
            currentBet += pot.getAmountOwed();
        }
        return currentBet;
    }

    public List<Pot> getPots() {
        return new ArrayList<Pot>(pots);
    }

    private void recurseSetAmountOwed(int pendingBet, int potCount) {
        Pot pot = pots.get(potCount);
        if (potCount + 1 == pots.size()) {
            pot.setAmountOwed(pendingBet);
            return;
        }
        pendingBet = pendingBet - pot.getAmountOwed();
        recurseSetAmountOwed(pendingBet, potCount + 1);
    }

    private void splitPot(Pot potToBeSplit, Player player, int currentDiff) {
        int diffBet = potToBeSplit.getAmountOwed() - currentDiff;
        potToBeSplit.setAmountOwed(potToBeSplit.getAmountOwed() - diffBet);

        Set<Player> eligiblePlayers = potToBeSplit.getEligiblePlayers();
        eligiblePlayers.remove(player);

        Pot newPot = new Pot(eligiblePlayers, diffBet);
        int index = pots.indexOf(potToBeSplit);
        pots.add(index + 1, newPot);
    }

}
