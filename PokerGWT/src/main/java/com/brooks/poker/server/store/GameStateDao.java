package com.brooks.poker.server.store;

import java.util.LinkedList;
import java.util.List;

import com.brooks.poker.client.model.CardCM;
import com.brooks.poker.client.model.GameStateCM;
import com.brooks.poker.client.model.PotState;
import com.brooks.poker.client.model.User;
import com.brooks.poker.server.convert.GameStateCMConverter;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

/**
 * @author Trevor
 *
 */
public class GameStateDao{

    public void saveGameState(GameStateCM gameState){
        GameStateCMConverter converter = new GameStateCMConverter();
        Entity entity = converter.convert(gameState);
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        datastore.put(entity);
    }

    public GameStateCM retrieveGameState(){
        GameStateCMConverter converter = new GameStateCMConverter();
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

        Query query = new Query(GameStateCMConverter.GAME_STATE_ENTITY);
        PreparedQuery prepared = datastore.prepare(query);

        Entity entity = prepared.asSingleEntity();
        if(entity == null){
            GameStateCM gameState = new GameStateCM();
            gameState.setAllUsers(new LinkedList<User>());
            gameState.setCommunityCards(new LinkedList<CardCM>());
            gameState.setPotState(new PotState());
            return gameState;
        }
        return converter.convert(entity);
    }

    public void savePendingGame(List<IndexedString> queryForPendingPlayers, long id){
        GameStateCM gameState = new GameStateCM();
        
        List<User> users = new LinkedList<User>();
        for(IndexedString is : queryForPendingPlayers){
            User user = new User();
            user.setName(is.getName());
            user.setIndex(is.getIndex());
            user.setChips(1000);
            users.add(user);
        }
        gameState.setAllUsers(users);
        gameState.setId(id);
        saveGameState(gameState);
    }

}
