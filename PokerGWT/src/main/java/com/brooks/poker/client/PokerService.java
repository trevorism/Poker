package com.brooks.poker.client;

import java.util.List;

import com.brooks.poker.client.model.Action;
import com.brooks.poker.client.model.GameStateCM;
import com.brooks.poker.client.model.User;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author Trevor
 *
 */
@RemoteServiceRelativePath("service")
public interface PokerService extends RemoteService{

    public void addUser(User user);
    public List<User> startHand(List<User> usersToPlay);
    public User actionOn();
    public GameStateCM placeBet(User user, Action action);
}
