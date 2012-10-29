package com.brooks.poker.client.push;

import com.brooks.poker.client.model.GameStateCM;

/**
 * @author Trevor
 *
 */
public class GameStateMessage implements PushEvent{

    private static final long serialVersionUID = 1L;

    private GameStateCM gameState;
    
    public GameStateMessage(){
    }

    public GameStateMessage(GameStateCM gameState){
        this.gameState = gameState;
    }

    public GameStateCM getGameState(){
        return gameState;
    }

    
}
