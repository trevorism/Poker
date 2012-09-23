package com.brooks.poker.client;

import java.util.List;

import com.brooks.poker.client.model.Action;
import com.brooks.poker.client.model.GameStateCM;
import com.brooks.poker.client.model.User;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * @author Trevor
 *
 */
public interface PokerServiceAsync{

    public void addUser(User user, AsyncCallback<Void> callback);

    public void actionOn(AsyncCallback<User> callback);

    public void startHand(List<User> usersToPlay, AsyncCallback<List<User>> callback);

    public void placeBet(User user, Action action, AsyncCallback<GameStateCM> callback);

}
