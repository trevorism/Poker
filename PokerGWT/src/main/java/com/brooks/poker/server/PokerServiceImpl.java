package com.brooks.poker.server;

import java.util.LinkedList;
import java.util.List;

import com.brooks.common.client.event.EventBus;
import com.brooks.poker.client.PokerException;
import com.brooks.poker.client.PokerService;
import com.brooks.poker.client.model.Action;
import com.brooks.poker.client.model.User;
import com.brooks.poker.client.push.UserMessage;
import com.brooks.poker.game.PokerGame;
import com.brooks.poker.game.data.BlindsAnte;
import com.brooks.poker.game.data.GameState;
import com.brooks.poker.player.Player;
import com.brooks.poker.server.model.PendingUser;
import com.brooks.poker.server.playerAction.EventDrivenPlayerAction;
import com.brooks.poker.server.playerAction.PlayerActionEvent;
import com.google.appengine.api.ThreadManager;
import com.google.appengine.api.channel.ChannelServiceFactory;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author Trevor
 * 
 */
public class PokerServiceImpl extends RemoteServiceServlet implements PokerService{

    private static final long serialVersionUID = 1L;

    @Override
    public String connectToChannel(){
        long id = DataStoreUtils.getPendingGameSequenceNumber();
        String channelId = DataStoreUtils.getChannelId(id);
        String gameToken = ChannelServiceFactory.getChannelService().createChannel(channelId);

        Thread thread = ThreadManager.createBackgroundThread(new SyncUsersWithClient(channelId));
        thread.start();
        return gameToken;
    }

    @Override
    public void addUser(UserMessage userAndIndex) throws PokerException{
        String username = userAndIndex.getUser().getName();

        List<PendingUser> pendingUsers = DataStoreUtils.queryForPendingPlayers();
        boolean found = findName(pendingUsers, username);
        if (found)
            throw new PokerException("A player already has joined the game with that name.");
        DataStoreUtils.addUser(userAndIndex);
    }

    private boolean findName(List<PendingUser> pendingUsers, String name){
        for (PendingUser pu : pendingUsers){
            if (pu.getName().equals(name))
                return true;
        }
        return false;
    }

    @Override
    public void startGame() throws PokerException{
        List<PendingUser> pendingUsers = DataStoreUtils.queryForPendingPlayers();
        long id = DataStoreUtils.getPendingGameSequenceNumber();
        final GameState gameState = createGameState(pendingUsers);
        gameState.setId(id);

        Thread thread = ThreadManager.createBackgroundThread(new Runnable(){

            @Override
            public void run(){
                PokerGame.playGame(gameState);
            }
        });
        thread.start();
        DataStoreUtils.incrementSequenceNumber();
        DataStoreUtils.deletePendingUsers();
    }

    private GameState createGameState(List<PendingUser> pendingUsers){
        List<Player> players = new LinkedList<>();
        for (PendingUser user : pendingUsers){
            Player player = new Player(user.getName(), 1000, new EventDrivenPlayerAction(user.getName()));
            players.add(player);
        }

        BlindsAnte blindsAnte = new BlindsAnte();
        blindsAnte.bigBlind = 25;
        blindsAnte.smallBlind = 10;

        return GameState.configureGameState(blindsAnte, players);
    }

    @Override
    public void placeBet(User user, Action action){
        PlayerActionEvent actionEvent = new PlayerActionEvent(user, action);
        EventBus.getInstance().fireEvent(actionEvent);
    }

}