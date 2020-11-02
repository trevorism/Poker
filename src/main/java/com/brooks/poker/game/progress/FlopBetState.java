/**
 * 
 */
package com.brooks.poker.game.progress;

import com.brooks.poker.game.GameActions;
import com.brooks.poker.game.data.GamePhase;
import com.brooks.poker.game.data.GameState;
import com.brooks.poker.player.Player;

/**
 * @author Trevor
 *
 */
public class FlopBetState extends BetState {
   
	public FlopBetState(GameState gameState){
        super(gameState);        
    }

    @Override
	public void handleState() {
        Player firstToAct = GameActions.getPlayerAfterDealer(gameState);    
        bettingRound(firstToAct);
    }
    
    @Override
    public GamePhase getNextPhase(){
        if(onePlayerInThePot())
            return GamePhase.END_HAND;
        
        return GamePhase.TURN;
    }
    
}
