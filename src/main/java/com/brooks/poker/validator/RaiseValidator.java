package com.brooks.poker.validator;

import com.brooks.poker.game.data.GameState;
import com.brooks.poker.game.data.pot.Pot;
import com.brooks.poker.player.Player;

/**
 * @author Trevor
 */
public class RaiseValidator implements BettingValidator {

    private int betMustBeAtLeast;
    private int playerChipCount;
    private GameState gameState;

    public RaiseValidator(GameState gameState, Player player) {
        this.gameState = gameState;
        playerChipCount = player.getChipCount();

        int currentBet = gameState.getPots().getCurrentBet();

        if (currentBet == 0) {
            betMustBeAtLeast = gameState.getBlindsAnte().bigBlind;
        } else {
            betMustBeAtLeast = currentBet - player.getPendingBet();
        }

    }

    @Override
    public boolean validateBet(int bet) {
        if (bet >= playerChipCount)
            return true;
        return bet >= betMustBeAtLeast;
    }

    @Override
    public int getMinRaise() {
        return Math.min(playerChipCount, betMustBeAtLeast);
    }

    public boolean canRaise() {
        return !isOnlyOneEligiblePlayerLeftForBetting(this.gameState);
    }

    private static boolean isOnlyOneEligiblePlayerLeftForBetting(GameState gameState) {
        for (Pot pot : gameState.getPots().getPots()) {
            if (pot.getEligiblePlayerCount() <= 1)
                return true;
        }
        return false;
    }
}
