package com.brooks.poker.client;

import com.brooks.poker.client.model.Action;
import com.brooks.poker.client.model.GameStateCM;
import com.brooks.poker.client.model.User;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * @author Trevor
 * 
 */
public interface PokerServiceAsync{

    public void connectToChannel(AsyncCallback<String> callback);

    public void addUser(User user, int index, AsyncCallback<Void> callback);

    public void startHand(AsyncCallback<Void> callback);

    public void placeBet(User user, Action action, AsyncCallback<GameStateCM> callback);


}
