package com.brooks.poker.server.store;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.brooks.poker.client.model.User;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

/**
 * @author Trevor
 *
 */
public class PendingPlayerDao{
    private static final String PENDING_PLAYER_ENTITY = "player-entity";
    private static final String PLAYER_NAME = "player-name";
    private static final String PLAYER_INDEX = "player-index";

    public List<IndexedString> queryForPendingPlayers(){
        List<IndexedString> pendingUserList = new LinkedList<IndexedString>();
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

        Query query = new Query(PENDING_PLAYER_ENTITY);
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
    

    public void addUser(User user){
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

        Entity entity = new Entity(PENDING_PLAYER_ENTITY);
        entity.setProperty(PLAYER_NAME, user.getName());
        entity.setProperty(PLAYER_INDEX, user.getIndex());
        
        datastore.put(entity);
    }

    public void deleteIndexedStrings(){
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

        Query query = new Query(PENDING_PLAYER_ENTITY);
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
