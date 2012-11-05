package com.brooks.poker.server.store;

import com.brooks.poker.client.model.GameStateCM;
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
        if(entity == null)
            return new GameStateCM();
        
        return converter.convert(entity);
    }

}
