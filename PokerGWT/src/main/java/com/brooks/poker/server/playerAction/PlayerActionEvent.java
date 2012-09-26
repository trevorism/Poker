package com.brooks.poker.server.playerAction;

import com.brooks.common.client.event.Event;
import com.brooks.poker.client.model.Action;
import com.brooks.poker.client.model.User;

/**
 * @author Trevor
 *
 */
public class PlayerActionEvent implements Event{

    private final User user;
    private final Action action;
    
    public PlayerActionEvent(User user, Action action){
        this.user = user;
        this.action = action;
    }

    public User getUser(){
        return user;
    }

    public Action getAction(){
        return action;
    }
    
}
