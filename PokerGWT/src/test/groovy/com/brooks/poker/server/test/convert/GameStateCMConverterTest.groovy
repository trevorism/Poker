package com.brooks.poker.server.test.convert;

import org.junit.Test

import com.brooks.poker.client.model.User
import com.brooks.poker.server.convert.GameStateCMConverter;

/**
 * @author Trevor
 *
 */
public class GameStateCMConverterTest{

    @Test
    public void testFindByIndex(){
        List<User> users = [new User(name:"Trevor"), new User(name:"Johnny"), new User(name:"Monkey")]
        GameStateCMConverter converter = new GameStateCMConverter()
        
        assert converter.findUserIndex(users, "Trevor") == 0
        assert converter.findUserIndex(users, "Johnny") == 1
        assert converter.findUserIndex(users, "Monkey") == 2
        assert converter.findUserIndex(users, "Fred") == -1
        
    }
}
