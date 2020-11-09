package com.brooks.poker.player;

import com.brooks.poker.cards.Card;
import com.brooks.poker.cards.Hand;
import com.brooks.poker.player.action.NullPlayerAction;
import com.brooks.poker.player.action.PlayerAction;

import java.util.Objects;

/**
 * @author Trevor TODO Make this immutable
 */
public class Player {

    public static final Player NOBODY = new Player("", 0, NullPlayerAction.INSTANCE);

    private final String name;
    private final Hand hand;
    private int chipCount;
    private int pendingBet;
    private PlayerAction action;

    public Player(String name, int chipCount, PlayerAction action) {
        this.name = name;
        this.hand = new Hand();
        this.chipCount = chipCount;
        this.pendingBet = 0;
        this.action = action;
    }

    public boolean isNullPlayer() {
        return name.equals("");
    }

    public void requestBet(int betAmount) {
        int actualAmountBet = actualAmountBet(betAmount);
        updateChipCount(actualAmountBet);
        pendingBet += actualAmountBet;
    }

    public int addPendingBetToPot(int amountNeededInPot) {
        int amountToBeAdded = actualAmountToBeAdded(amountNeededInPot);
        updatePendingBet(amountToBeAdded);
        return amountToBeAdded;
    }

    public void reset() {
        hand.reset();
        pendingBet = 0;
    }

    public boolean isAllIn() {
        return chipCount == 0;
    }

    public void addChips(int chipAmount) {
        chipCount += chipAmount;
    }

    public void addCard(Card card) {
        hand.addCard(card);
    }

    public int getPendingBet() {
        return pendingBet;
    }

    public int getChipCount() {
        return chipCount;
    }

    public Hand getHand() {
        return hand;
    }

    public PlayerAction getAction() {
        return action;
    }

    public String getName() {
        return name;
    }

    private void updateChipCount(int actualAmountBet) {
        chipCount = chipCount - actualAmountBet;
    }

    private int actualAmountBet(int betAmount) {
        if (betAmount >= chipCount) {
            betAmount = chipCount;
        }
        return betAmount;
    }

    private int actualAmountToBeAdded(int requestedAmount) {
        return Math.min(requestedAmount, pendingBet);
    }

    private void updatePendingBet(int amountToBeAdded) {
        if (amountToBeAdded < pendingBet)
            pendingBet = pendingBet - amountToBeAdded;
        else
            pendingBet = 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name);
        sb.append(" (");
        sb.append(chipCount);
        sb.append(")");
        return sb.toString();
    }

}
