package com.brooks.poker.server.test

import com.brooks.poker.client.model.GameStateCM
import com.brooks.poker.game.data.BlindsAnte
import com.brooks.poker.game.data.GamePhase
import com.brooks.poker.game.data.GameState
import com.brooks.poker.game.states.GameStateFactory
import com.brooks.poker.game.states.GameStateHandler
import com.brooks.poker.player.Player
import com.brooks.poker.player.action.AlwaysCallPlayerAction
import com.brooks.poker.server.convert.GameStateCMConverter
import com.brooks.poker.server.store.GameStateDao
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig
import com.google.appengine.tools.development.testing.LocalServiceTestHelper

import org.junit.After
import org.junit.Before
import org.junit.Test

/**
 * @author Trevor
 *
 */
class TestPlayGame{

    private final LocalServiceTestHelper helper = new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());
    private final GameStateDao dao = new GameStateDao();

    @Before
    public void setUp(){
        helper.setUp();
    }

    @After
    public void tearDown(){
        helper.tearDown();
    }
    
    @Test 
    public void playGame(){
        GameStateCM cm = new GameStateCM()
        dao.saveGameState(cm);
        GameState gameState = playALittleBit();
        GameStateCMConverter converter = new GameStateCMConverter();
        cm = converter.convert(gameState);
        assert cm.getPotState().hasOnePot() == true
        assert cm.getPotState().getPots().get(0).pot == 50
        dao.saveGameState(cm);
        cm = dao.retrieveGameState()
        assert cm.getPotState().hasOnePot() == true
        assert cm.getPotState().getPots().get(0).pot == 50
    }
    
    private GameState playALittleBit() {
        BlindsAnte blindsAnte = createBlindsAnte()
        List<Player> players = createPlayers()
        GameState gameState = GameState.configureGameState(blindsAnte, players)
        gameState.setId(2);
        
        GameStateFactory factory = new GameStateFactory(gameState);
        GamePhase currentPhase = GamePhase.BEGIN_HAND;

        while (!currentPhase.equals(GamePhase.FLOP_BET)){
            GameStateHandler handler = factory.getStateHandler(currentPhase);
            handler.handleState();
            currentPhase = handler.getNextPhase();
        }

        
        return gameState
    }

    private BlindsAnte createBlindsAnte() {
        BlindsAnte blindsAnte = new BlindsAnte()
        blindsAnte.ante = 0;
        blindsAnte.bigBlind = 25;
        blindsAnte.smallBlind = 10
        return blindsAnte
    }

    private List createPlayers() {
        List<Player> players = []
        players.add(new Player("Trevor",1000, new AlwaysCallPlayerAction()));
        players.add(new Player("Brooks",1000, new AlwaysCallPlayerAction()));
        return players
    }
}
