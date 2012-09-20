/**
 * 
 */
package com.brooks.poker.game.states;

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
 * 
 */
public abstract class BetState extends GameStateHandler{

    public BetState(GameState gameState){
        super(gameState);

    }

    public void bettingRound(Player startPlayer){
        startPlayer = firstPlayerBets(startPlayer);

        if (shouldEndRound())
            return;

        Table table = gameState.getTable();
        Player endPlayer = getEndPlayer(startPlayer);
        startPlayer = table.getNextActivePlayer(startPlayer);

        eachPlayerAfterTheFirstBets(startPlayer, endPlayer);
    }

    private Player firstPlayerBets(Player startPlayer){
        startPlayer = ensurePlayerIsActive(startPlayer);
        modifyGameState(startPlayer);
        return startPlayer;
    }

    private void eachPlayerAfterTheFirstBets(Player startPlayer, Player endPlayer){
        Table table = gameState.getTable();
        while (!startPlayer.equals(endPlayer)){
            modifyGameState(startPlayer);
            if (shouldEndRound())
                return;

            if (hasMaxBet(endPlayer, startPlayer)){
                endPlayer = startPlayer;
            }

            startPlayer = table.getNextActivePlayer(startPlayer);
        }
        gameState.endBettingRound();
    }

    private void modifyGameState(Player player){
        PlayerAction playerAction = player.getAction();
        BettingOutcome outcome = playerAction.getBettingOutcome(gameState, player);
        outcome.modifyGameState(gameState, player);
    }

    protected void betBlindAnte(Player player, int amount){
        BettingOutcome outcome = BettingOutcomeFactory.createBlindsOutcome(amount);
        outcome.modifyGameState(gameState, player);
    }

    protected boolean onePlayerInThePot(){
        Pots pots = gameState.getPots();

        for (Pot pot : pots.getPots()){
            int count = pot.getEligiblePlayerCount();
            if (count > 1)
                return false;
        }
        return true;
    }

    private boolean onePlayerAtTheTable(){
        Table table = gameState.getTable();
        if (table.getActivePlayersSize() <= 1)
            return true;
        return false;
    }

    private boolean shouldEndRound(){
        if (onePlayerInThePot() || onePlayerAtTheTable()){
            gameState.endBettingRound();
            return true;
        }
        return false;
    }

    private Player getEndPlayer(Player defaultPlayer){
        int maxBet = 0;
        Player endPlayer = Player.NOBODY;
        Table table = gameState.getTable();

        for (Player player : table.getSortedActivePlayers()){
            if (player.getPendingBet() > maxBet){
                maxBet = player.getPendingBet();
                endPlayer = player;
            }
        }

        if (endPlayer.isNullPlayer()){
            return defaultPlayer;
        }
        return endPlayer;

    }

    private Player ensurePlayerIsActive(Player startPlayer){
        Table table = gameState.getTable();
        if (table.isInactive(startPlayer)){
            startPlayer = table.getNextActivePlayer(startPlayer);
        }
        return startPlayer;
    }

    private boolean hasMaxBet(Player prevPlayer, Player player){
        if (player.getPendingBet() > prevPlayer.getPendingBet())
            return true;
        return false;
    }

}
