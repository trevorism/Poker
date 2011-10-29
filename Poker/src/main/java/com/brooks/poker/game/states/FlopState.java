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
public class FlopState extends GameStateHandler {

    public FlopState(GameState gameState){
        super(gameState);
        
    }

    @Override
    public void handleState(){
        gameState.burnCard();

        gameState.dealCommunityCard();
        gameState.dealCommunityCard();
        gameState.dealCommunityCard();       
    }

    @Override
    public GamePhase getNextPhase(){
        
        return GamePhase.FLOP_BET;
    }

}
