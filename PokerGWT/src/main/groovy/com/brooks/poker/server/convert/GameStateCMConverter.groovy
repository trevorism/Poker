package com.brooks.poker.server.convert

import com.brooks.poker.client.model.GameStateCM;
import com.brooks.poker.server.game.GameStateData;

/**
 * @author Trevor
 *
 */
class GameStateCMConverter{

    public GameStateCM convert(GameStateData data){
        
        4.times { println "Holla ${it}" }
        return null
    }
}
