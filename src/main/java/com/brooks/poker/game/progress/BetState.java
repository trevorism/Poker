package com.brooks.poker.game.progress;

import com.brooks.poker.game.BettingRound;
import com.brooks.poker.game.GameActions;
import com.brooks.poker.game.data.GameState;
import com.brooks.poker.game.data.Table;
import com.brooks.poker.game.data.pot.Pot;
import com.brooks.poker.game.data.pot.Pots;
import com.brooks.poker.outcome.BettingOutcome;
import com.brooks.poker.outcome.BettingOutcomeFactory;
import com.brooks.poker.player.Player;
import com.brooks.poker.player.action.PlayerAction;

/**
 * @author Trevor
 */
public abstract class BetState extends GameProgressHandler {

    private Player actionOnPlayer;

    public BetState(GameState gameState) {
        super(gameState);
    }

    public void bettingRound(Player startPlayer) {
        Table table = gameState.getTable();
        BettingRound bettingRound = new BettingRound(gameState, startPlayer, false);
        actionOnPlayer = startPlayer;
        while (!bettingRound.isComplete()){
            actionOnPlayer = ensurePlayerIsActive(actionOnPlayer);
            int currentBet = gameState.getPots().getCurrentBet();
            modifyGameState(actionOnPlayer);
            bettingRound.actionComplete(actionOnPlayer);
            if(gameState.getPots().getCurrentBet() != currentBet){
                bettingRound = new BettingRound(gameState, actionOnPlayer, true);
            }
            actionOnPlayer = table.getNextActivePlayer(actionOnPlayer);
        }
        GameActions.endBettingRound(gameState);
    }

    private void modifyGameState(Player player) {
        PlayerAction playerAction = player.getAction();
        BettingOutcome outcome = playerAction.getBettingOutcome(gameState, player);
        outcome.modifyGameState(gameState, player);
    }

    protected void betBlindAnte(Player player, int amount) {
        BettingOutcome outcome = BettingOutcomeFactory.createBlindsOutcome(amount);
        outcome.modifyGameState(gameState, player);
    }

    public boolean onePlayerInThePot() {
        Pots pots = gameState.getPots();

        for (Pot pot : pots.getPots()) {
            int count = pot.getEligiblePlayerCount();
            if (count > 1)
                return false;
        }
        return true;
    }

    private boolean onePlayerAtTheTable() {
        Table table = gameState.getTable();
        return table.getActivePlayersSize() <= 1;
    }

    private boolean shouldEndRound() {
        if (onePlayerInThePot() || onePlayerAtTheTable()) {
            GameActions.endBettingRound(gameState);
            return true;
        }
        return false;
    }

    private Player getEndPlayer() {
        int maxBet = 0;
        Player endPlayer = Player.NOBODY;
        Table table = gameState.getTable();

        for (Player player : table.getSortedActivePlayers()) {
            if (player.getPendingBet() > maxBet) {
                maxBet = player.getPendingBet();
                endPlayer = player;
            }
        }

        if (endPlayer.isNullPlayer()) {
            return actionOnPlayer;
        }
        return endPlayer;

    }

    private Player ensurePlayerIsActive(Player startPlayer) {
        Table table = gameState.getTable();
        if (table.isInactive(startPlayer)) {
            startPlayer = table.getNextActivePlayer(startPlayer);
        }
        return startPlayer;
    }

    private boolean hasMaxBet(Player prevPlayer, Player player) {
        return player.getPendingBet() > prevPlayer.getPendingBet();
    }

    public Player getActionOnPlayer() {
        return actionOnPlayer;
    }

}
