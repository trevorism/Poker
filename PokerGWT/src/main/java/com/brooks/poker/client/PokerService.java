package com.brooks.poker.client;

import com.brooks.poker.client.model.Action;
import com.brooks.poker.client.model.GameStateCM;
import com.brooks.poker.client.model.User;
import com.brooks.poker.client.push.UserMessage;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author Trevor
 * 
 */
@RemoteServiceRelativePath("service")
public interface PokerService extends RemoteService{

    public String connectToChannel();
    
    public void addUser(UserMessage userAtTable) throws PokerException;

    public void startGame() throws PokerException;

    public GameStateCM placeBet(User user, Action action);

}
