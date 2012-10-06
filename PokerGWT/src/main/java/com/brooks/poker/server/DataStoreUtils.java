package com.brooks.poker.server;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.brooks.poker.client.push.UserMessage;
import com.brooks.poker.server.model.PendingUser;
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
public class DataStoreUtils{

    public static final String SEQUENCE_NUMBER_ENTITY = "sequence-number";
    public static final String SEQUENCE_NUMBER = "number";

    public static final String PENDING_PLAYER_ENTITY = "player-entity";
    public static final String PLAYER_NAME = "player-name";
    public static final String PLAYER_INDEX = "player-index";
    
    public static long getPendingGameSequenceNumber(){
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

        Query query = new Query(SEQUENCE_NUMBER_ENTITY);
        PreparedQuery prepared = datastore.prepare(query);

        Entity entity = prepared.asSingleEntity();
        return getSequenceNumberFromEntity(entity);
    }

    private static long getSequenceNumberFromEntity(Entity entity){
        if (entity == null){
            return 0;
        }
        return (Long) entity.getProperty(SEQUENCE_NUMBER);
    }
    
    public static void incrementSequenceNumber(){
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        long seqNumber = getPendingGameSequenceNumber();
        
        Entity entity = new Entity(SEQUENCE_NUMBER_ENTITY,SEQUENCE_NUMBER_ENTITY);
        entity.setProperty(SEQUENCE_NUMBER, seqNumber+1);

        datastore.put(entity);
    }
    
    public static List<PendingUser> queryForPendingPlayers(){
        List<PendingUser> pendingUserList = new LinkedList<PendingUser>();
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

        Query query = new Query(PENDING_PLAYER_ENTITY);
        PreparedQuery prepared = datastore.prepare(query);
        
        Iterator<Entity> iterable = prepared.asIterator();
        while(iterable.hasNext()){
            Entity userEntity = iterable.next();
            String name = (String)userEntity.getProperty(PLAYER_NAME);
            Integer index = ((Number)userEntity.getProperty(PLAYER_INDEX)).intValue();
            PendingUser pendingUser = new PendingUser(name, index);
            pendingUserList.add(pendingUser);
        }

        return pendingUserList;
    }
    
    public static String getChannelId(long id){
        return "POKER_GAME_" + id;
    }
    
    public static void addUser(UserMessage userAndIndex){
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        String username = userAndIndex.getUser().getName();

        Entity entity = new Entity(DataStoreUtils.PENDING_PLAYER_ENTITY);
        entity.setProperty(DataStoreUtils.PLAYER_NAME, username);
        entity.setProperty(DataStoreUtils.PLAYER_INDEX, userAndIndex.getIndex());
        datastore.put(entity);
    }

    public static void deletePendingUsers(){
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

        Query query = new Query(PENDING_PLAYER_ENTITY);
        PreparedQuery prepared = datastore.prepare(query);

        List<Key> keys = new LinkedList<>();
        Iterator<Entity> iterable = prepared.asIterator();
        while(iterable.hasNext()){
            Entity userEntity = iterable.next();
            keys.add(userEntity.getKey());
        }
        datastore.delete(keys);
        
    }
}
