package com.brooks.poker.player.action;

import com.brooks.poker.game.data.GameState;
import com.brooks.poker.outcome.BettingOutcome;
import com.brooks.poker.outcome.BettingOutcomeFactory;
import com.brooks.poker.player.Player;
import com.brooks.poker.player.ui.ActionResult;
import com.brooks.poker.player.ui.PokerActionPrinter;
import com.brooks.poker.player.ui.UserInput;
import com.brooks.poker.validator.RaiseValidator;

/**
 * @author Trevor
 * 
 */
public class HumanPlayerAction implements PlayerAction{

    @Override
    public BettingOutcome getBettingOutcome(GameState gameState, Player player){
        PokerActionPrinter.printGameState(gameState);
        PokerActionPrinter.printPrivatePlayer(player);

        if (gameState.getPots().getCurrentBet() == 0){
            ActionResult result = UserInput.checkBet();
            return bettingOutcomeFromActionResult(gameState, player, result);
        }

        ActionResult result = UserInput.foldCallRaise();
        return bettingOutcomeFromActionResult(gameState, player, result);
    }

    private int getBetAmount(GameState gameState, Player player){
        return UserInput.betPrompt(new RaiseValidator(gameState, player));
    }
    
    private BettingOutcome bettingOutcomeFromActionResult(GameState gameState, Player player, ActionResult result){
        switch (result){
            case FOLD:
                return BettingOutcomeFactory.createFoldOutcome();
            case CALL:
                return BettingOutcomeFactory.createCallOutcome();
            case BET:
                return BettingOutcomeFactory.createRaiseOutcome(getBetAmount(gameState, player));
        }
        return BettingOutcomeFactory.createFoldOutcome();

    }
}
