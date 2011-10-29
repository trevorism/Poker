/**
 * 
 */
package com.brooks.poker.game.states;

import com.brooks.poker.game.data.GamePhase;
import com.brooks.poker.game.data.GameState;

/**
 * @author Trevor
 *
 */
public class TurnState extends GameStateHandler {

    public TurnState(GameState gameState){
        super(gameState);
    }

    @Override
    public void handleState(){
        gameState.burnCard();
        gameState.dealCommunityCard();
    }

    @Override
    public GamePhase getNextPhase(){        
        return GamePhase.TURN_BET;
    }
}