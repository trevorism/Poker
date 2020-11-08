package com.brooks.poker.outcome;

import com.brooks.poker.game.data.GameState;
import com.brooks.poker.player.Player;
import com.brooks.poker.validator.RaiseValidator;

/**
 * @author Trevor
 */
public class RaiseOutcome extends CallOutcome {

    protected int fixedBet;

    protected RaiseOutcome(int fixedBet) {
        this.fixedBet = fixedBet;
    }

    @Override
    public int getBetAmount(GameState gameState, Player player) {
        RaiseValidator raiseValidator = new RaiseValidator(gameState, player);

        if (!raiseValidator.canRaise()) {
            return gameState.getPots().getCurrentBet() - player.getPendingBet();
        }

        if (!raiseValidator.validateBet(fixedBet)) {
            return raiseValidator.getMinRaise();
        }
        return fixedBet;
    }

}
