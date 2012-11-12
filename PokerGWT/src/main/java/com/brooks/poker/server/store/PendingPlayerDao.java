package com.brooks.poker.server.store;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.brooks.poker.client.model.User;
import com.google.appengine.api.datastore.*;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;

/**
 * @author Trevor
 *
 */
public class PendingPlayerDao{
    private static final String PENDING_PLAYER_ENTITY = "player-entity";
    private static final String PLAYER_NAME = "player-name";
    private static final String PLAYER_INDEX = "player-index";
    private static final String GAME_ID = "game-id";

    public List<IndexedString> queryForPendingPlayers(long gameId){
        List<IndexedString> pendingUserList = new LinkedList<IndexedString>();
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

        Filter filter = new FilterPredicate(GAME_ID, FilterOperator.EQUAL, gameId);
        Query query = new Query(PENDING_PLAYER_ENTITY).setFilter(filter);
        PreparedQuery prepared = datastore.prepare(query);
        
        Iterator<Entity> iterable = prepared.asIterator();
        while(iterable.hasNext()){
            Entity userEntity = iterable.next();
            String name = (String)userEntity.getProperty(PLAYER_NAME);
            Integer index = ((Number)userEntity.getProperty(PLAYER_INDEX)).intValue();
            IndexedString pendingUser = new IndexedString(index, name);
            pendingUserList.add(pendingUser);
        }

        return pendingUserList;
    }
    

    public void addUser(User user, long gameId){
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

        Entity entity = new Entity(PENDING_PLAYER_ENTITY);
        entity.setProperty(GAME_ID, gameId);
        entity.setProperty(PLAYER_NAME, user.getName());
        entity.setProperty(PLAYER_INDEX, user.getIndex());
        
        datastore.put(entity);
    }

    public void deleteIndexedStrings(long gameId){
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

        Filter filter = new FilterPredicate(GAME_ID, FilterOperator.EQUAL, gameId);
        Query query = new Query(PENDING_PLAYER_ENTITY).setFilter(filter);
        PreparedQuery prepared = datastore.prepare(query);

        List<Key> keys = new LinkedList<Key>();
        Iterator<Entity> iterable = prepared.asIterator();
        while(iterable.hasNext()){
            Entity userEntity = iterable.next();
            keys.add(userEntity.getKey());
        }
        datastore.delete(keys);        
    }
}
