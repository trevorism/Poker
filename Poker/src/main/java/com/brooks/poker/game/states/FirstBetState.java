/**
 * 
 */
package com.brooks.poker.game.states;

import com.brooks.poker.command.PlayerCommand;
import com.brooks.poker.game.data.BlindsAnte;
import com.brooks.poker.game.data.GamePhase;
import com.brooks.poker.game.data.GameState;
import com.brooks.poker.game.data.Table;
import com.brooks.poker.player.Player;

/**
 * @author Trevor Small blind, big blind, and a round of betting.
 */
public class FirstBetState extends BetState{

    public FirstBetState(GameState gameState){
        super(gameState);
    }

    @Override
    public void handleState(){
        ante();
        Player actionOn = blinds();
        bettingRound(actionOn);
    }

    @Override
    public GamePhase getNextPhase(){
        if (shouldEndHand())
            return GamePhase.END_HAND;

        return GamePhase.FLOP;
    }

    private void ante(){
        Table table = gameState.getTable();
        table.executeOnEachActivePlayer(getPlayerAfterDealer(), new AnteCommand());
        gameState.endBettingRound();
    }

    private Player blinds(){
        BlindsAnte blinds = gameState.getBlindsAnte();
        Player player = getPlayerAfterDealer();

        betBlindAnte(player, blinds.smallBlind);

        Table table = gameState.getTable();
        player = table.getNextActivePlayer(player);

        betBlindAnte(player, blinds.bigBlind);

        return table.getNextActivePlayer(player);
    }

    private class AnteCommand implements PlayerCommand{
        @Override
        public void execute(Player player){
            int ante = gameState.getBlindsAnte().ante;
            betBlindAnte(player, ante);
        }
    }
}
