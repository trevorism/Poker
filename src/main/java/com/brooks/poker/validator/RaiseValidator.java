package com.brooks.poker.validator;

import com.brooks.poker.game.data.GameState;
import com.brooks.poker.player.Player;

/**
 * @author Trevor
 *
 */
public class RaiseValidator implements BettingValidator{

    private int betMustBeAtLeast;
    private int playerChipCount;
    
    public RaiseValidator(GameState gameState, Player player){
        playerChipCount = player.getChipCount();
        
        int currentBet = gameState.getPots().getCurrentBet();
        
        if(currentBet == 0){
            betMustBeAtLeast = gameState.getBlindsAnte().bigBlind;
        }
        else{
            betMustBeAtLeast = currentBet * 2 - player.getPendingBet();            
        }
        
    }

    @Override
    public boolean validateBet(int bet){
        if(bet >= playerChipCount)
            return true;
        return bet >= betMustBeAtLeast;
    }

    @Override
    public int getMinRaise(){       
        return Math.min(playerChipCount, betMustBeAtLeast);
    }
    
}
