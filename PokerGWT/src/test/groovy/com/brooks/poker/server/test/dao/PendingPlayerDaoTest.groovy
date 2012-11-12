package com.brooks.poker.server.test.dao;

import org.junit.After
import org.junit.Before
import org.junit.Test

import com.brooks.poker.client.model.User
import com.brooks.poker.server.store.IndexedString
import com.brooks.poker.server.store.PendingPlayerDao
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig
import com.google.appengine.tools.development.testing.LocalServiceTestHelper

import static org.junit.Assert.assertEquals

/**
 * @author Trevor
 *
 */
public class PendingPlayerDaoTest{

    private final LocalServiceTestHelper helper = new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());
    private final PendingPlayerDao dao = new PendingPlayerDao();
    
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
        assert dao.queryForPendingPlayers(0).isEmpty() == true
    }
    
    @Test
    public void testRetrieve(){
        assert 0 == dao.queryForPendingPlayers(0).size()
        
        dao.addUser(new User(name: "name", index: 2),0)
        assert 0 == dao.queryForPendingPlayers(1).size()
        
        List userList = dao.queryForPendingPlayers(0)
        assert 1 == userList.size()
        IndexedString is = userList.get(0)
        assert is.name == "name"
        assert is.index == 2
        
        dao.deleteIndexedStrings(0)
        
        assert 0 == dao.queryForPendingPlayers(0).size()
        assert 0 == dao.queryForPendingPlayers(1).size()
    }

}
