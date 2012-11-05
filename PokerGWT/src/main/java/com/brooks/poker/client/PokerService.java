package com.brooks.poker.client;

import com.brooks.poker.client.model.Action;
import com.brooks.poker.client.model.User;
import com.brooks.poker.client.push.PushEvent;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author Trevor
 * 
 */
@RemoteServiceRelativePath("service")
public interface PokerService extends RemoteService{

    public String connectToChannel();
    
    public void addUser(User userAtTable) throws PokerException;

    public void startGame() throws PokerException;

    public void placeBet(User user, Action action);

    public PushEvent receiveServerPush(String key);
}
