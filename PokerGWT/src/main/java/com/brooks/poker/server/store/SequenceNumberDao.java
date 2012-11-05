package com.brooks.poker.server.store;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

/**
 * @author Trevor
 * 
 */
public class SequenceNumberDao{

    private static final String SEQUENCE_NUMBER_ENTITY = "sequence-number";
    private static final String SEQUENCE_NUMBER = "number";
    
    public void incrementSequenceNumber(){
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        long seqNumber = getPendingGameSequenceNumber();
        
        Entity entity = new Entity(SEQUENCE_NUMBER_ENTITY,SEQUENCE_NUMBER_ENTITY);
        entity.setProperty(SEQUENCE_NUMBER, seqNumber+1);

        datastore.put(entity);
    }

    public long getPendingGameSequenceNumber(){
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
        return ((Number) entity.getProperty(SEQUENCE_NUMBER)).longValue();
    }
}
