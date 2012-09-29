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

    private Player actionOnPlayer;
    
    public BetState(GameState gameState){
        super(gameState);
    }

    public void bettingRound(Player startPlayer){
        actionOnPlayer = startPlayer;
        firstPlayerBets();

        if (shouldEndRound())
            return;

        Table table = gameState.getTable();
        Player endPlayer = getEndPlayer();
        actionOnPlayer = table.getNextActivePlayer(actionOnPlayer);

        eachPlayerAfterTheFirstBets(endPlayer);
    }

    private void firstPlayerBets(){
        actionOnPlayer = ensurePlayerIsActive(actionOnPlayer);
        modifyGameState(actionOnPlayer);
    }

    private void eachPlayerAfterTheFirstBets(Player endPlayer){
        Table table = gameState.getTable();
        while (!actionOnPlayer.equals(endPlayer)){
            modifyGameState(actionOnPlayer);
            if (shouldEndRound())
                return;

            if (hasMaxBet(endPlayer, actionOnPlayer)){
                endPlayer = actionOnPlayer;
            }

            actionOnPlayer = table.getNextActivePlayer(actionOnPlayer);
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

    private Player getEndPlayer(){
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
            return actionOnPlayer;
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

    public Player getActionOnPlayer(){
        return actionOnPlayer;
    }

}
