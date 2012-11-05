package com.brooks.poker.server.test.dao;

import org.junit.After
import org.junit.Before
import org.junit.Test

import com.brooks.poker.client.model.GameStateCM
import com.brooks.poker.game.data.BlindsAnte
import com.brooks.poker.game.data.GameState
import com.brooks.poker.player.Player
import com.brooks.poker.player.action.NullPlayerAction
import com.brooks.poker.server.convert.GameStateCMConverter
import com.brooks.poker.server.store.GameStateDao
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig
import com.google.appengine.tools.development.testing.LocalServiceTestHelper

/**
 * @author Trevor
 *
 */
public class GameStateDaoTest{

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
    public void testQuery(){
        GameStateCM clientModel = dao.retrieveGameState()
        assert clientModel.id == -1
        
        clientModel = createClientModel()
        dao.saveGameState(clientModel)
        
        assertClientModel(clientModel)
        clientModel = dao.retrieveGameState()
        assertClientModel(clientModel)        
    }

	private assertClientModel(GameStateCM clientModel) {
		assert clientModel.id == 2
		assert clientModel.allUsers.size() == 3
		assert clientModel.communityCards.size() == 0
		assert clientModel.started == false
	}

	private GameStateCM createClientModel() {
		BlindsAnte blindsAnte = createBlindsAnte()
		List<Player> players = createPlayers()
		GameState gameState = GameState.configureGameState(blindsAnte, players)
        gameState.setId(2);
		GameStateCMConverter converter = new GameStateCMConverter();
		GameStateCM clientModel = converter.convert(gameState, false)
		return clientModel
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
        players.add(new Player("Trevor",1000, new NullPlayerAction()));
        players.add(new Player("Brooks",1000, new NullPlayerAction()));
        players.add(new Player("Vaughn",1000, new NullPlayerAction()));        
		return players
	}
    
}
