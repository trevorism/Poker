package com.brooks.poker.server.test.convert;

import org.junit.Test

import com.brooks.poker.client.model.GameStateCM
import com.brooks.poker.client.model.User
import com.brooks.poker.game.data.BlindsAnte
import com.brooks.poker.game.data.GameState
import com.brooks.poker.player.Player
import com.brooks.poker.player.action.NullPlayerAction
import com.brooks.poker.server.convert.GameStateCMConverter
import com.brooks.poker.server.game.GameStateData
import com.ibm.icu.impl.Assert;

/**
 * @author Trevor
 *
 */
public class GameStateCMConverterTest{
   
    @Test
    public void testConvert(){
        BlindsAnte ba = new BlindsAnte(bigBlind: 20, smallBlind:10)
        GameState gameState = GameState.configureGameState(ba, []);
        gameState.table.joinTable(new Player("Trevor",1000,new NullPlayerAction()))
        gameState.table.joinTable(new Player("Brooks",1000,new NullPlayerAction()))
        
        GameStateCMConverter converter = new GameStateCMConverter()
        GameStateData data = new GameStateData("asdf", gameState);
        
        GameStateCM clientModel = converter.convert(data)
        
        assert clientModel
        assert clientModel.allUsers
        assert clientModel.allUsers.size() == 2
    }
}
