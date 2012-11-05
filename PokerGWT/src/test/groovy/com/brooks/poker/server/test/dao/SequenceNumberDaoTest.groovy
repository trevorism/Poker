package com.brooks.poker.server.test.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.brooks.poker.server.store.SequenceNumberDao;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

import static org.junit.Assert.assertEquals;

/**
 * @author Trevor
 * 
 */
public class SequenceNumberDaoTest{

    private final LocalServiceTestHelper helper = new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());
    private final SequenceNumberDao dao = new SequenceNumberDao();
    
    @Before
    public void setUp(){
        helper.setUp();
    }

    @After
    public void tearDown(){
        helper.tearDown();
    }

    @Test
    public void testIncrement(){
        dao.incrementSequenceNumber();
        assertEquals(1, dao.getPendingGameSequenceNumber());
    }
    
    @Test
    public void testRetrieve(){
        assertEquals(0, dao.getPendingGameSequenceNumber());
    }

}
