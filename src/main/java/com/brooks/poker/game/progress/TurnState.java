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
public class TurnState extends GameProgressHandler {

    public TurnState(GameState gameState){
        super(gameState);
    }

    @Override
    public void handleState(){
    	GameActions.burnCard(gameState.getDeck());
    	GameActions.dealCommunityCard(gameState);
    }

    @Override
    public GamePhase getNextPhase(){        
        return GamePhase.TURN_BET;
    }
}