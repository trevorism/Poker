package com.brooks.poker.server.convert;

import com.brooks.poker.client.model.User
import com.brooks.poker.player.Player
import com.brooks.poker.server.playerAction.EventDrivenPlayerAction;

/**
 * @author Trevor
 *
 */
public class UserPlayerConverter{

    public Player createNewPlayerFromUser(User user){        
        new Player(user.name, 1000, new EventDrivenPlayerAction())       
    }

    public List<User> convert(List<Player> players){
        return null
    }
}
