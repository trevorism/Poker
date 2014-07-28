/**
 * 
 */
package com.brooks.poker.game.progress;

import com.brooks.poker.game.data.GamePhase;
import com.brooks.poker.game.data.GameState;


/**
 * @author Trevor
 *
 */
public class RiverState extends TurnState {

    public RiverState(GameState gameState){
        super(gameState);
        
    }
    
    @Override
    public GamePhase getNextPhase(){        
        return GamePhase.RIVER_BET;
    }
}
