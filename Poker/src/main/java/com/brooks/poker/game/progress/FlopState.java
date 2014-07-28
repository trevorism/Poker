/**
 * 
 */
package com.brooks.poker.game.progress;

import com.brooks.poker.game.GameActions;
import com.brooks.poker.game.data.GamePhase;
import com.brooks.poker.game.data.GameState;

/**
 * @author Trevor
 *
 */
public class FlopState extends GameProgressHandler {

    public FlopState(GameState gameState){
        super(gameState);        
    }

    @Override
    public void handleState(){
    	GameActions.burnCard(gameState.getDeck());

    	GameActions.dealCommunityCard(gameState);
    	GameActions.dealCommunityCard(gameState);
    	GameActions.dealCommunityCard(gameState);       
    }

    @Override
    public GamePhase getNextPhase(){        
        return GamePhase.FLOP_BET;
    }

}
