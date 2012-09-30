package com.brooks.poker.client.push;

import no.eirikb.gwtchannelapi.client.Message;

import com.brooks.common.client.event.Event;
import com.brooks.poker.client.model.GameStateCM;

/**
 * @author Trevor
 *
 */
public class GameStateMessage implements Message, Event{

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
