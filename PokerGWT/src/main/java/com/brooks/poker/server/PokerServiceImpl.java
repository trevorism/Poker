package com.brooks.poker.server;

import no.eirikb.gwtchannelapi.server.ChannelServer;

import com.brooks.common.client.event.EventBus;
import com.brooks.poker.client.PokerException;
import com.brooks.poker.client.PokerService;
import com.brooks.poker.client.model.Action;
import com.brooks.poker.client.model.User;
import com.brooks.poker.client.push.UserMessage;
import com.brooks.poker.player.Player;
import com.brooks.poker.server.convert.UserPlayerConverter;
import com.brooks.poker.server.game.GameServer;
import com.brooks.poker.server.game.GameStateData;
import com.brooks.poker.server.playerAction.PlayerActionEvent;
import com.google.appengine.api.ThreadManager;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author Trevor
 * 
 */
public class PokerServiceImpl extends RemoteServiceServlet implements PokerService{

    private static final long serialVersionUID = 1L;

    @Override
    public String connectToChannel(){
        Thread thread = ThreadManager.createBackgroundThread(new SyncUsersWithClient());
        thread.start();
        return GameServer.getInstance().getGameToken();
    }

    @Override
    public void addUser(UserMessage userAndIndex) throws PokerException{
        UserPlayerConverter userPlayerConverter = new UserPlayerConverter();
        Player player = userPlayerConverter.createNewPlayerFromUser(userAndIndex.getUser());
        GameServer.getInstance().addPlayer(userAndIndex.getIndex(), player);
        ChannelServer.send(GameServer.getInstance().getLatestChannelKey(), userAndIndex);
    }

    @Override
    public void startGame() throws PokerException{
        final GameStateData data = GameServer.getInstance().createGameState();

        if (data.getGameState().invalid())
            throw new PokerException("Not enough players in the game to start.");

        Thread thread = ThreadManager.createBackgroundThread(new Runnable(){

            @Override
            public void run(){
                data.startGame();
            }
        });
        thread.start();
    }

    @Override
    public void placeBet(User user, Action action){
        PlayerActionEvent actionEvent = new PlayerActionEvent(user, action);
        EventBus.getInstance().fireEvent(actionEvent);
    }

}